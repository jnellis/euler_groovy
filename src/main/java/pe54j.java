import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Poker hands
 * Problem 54
 * <p>
 * In the card game poker, a hand consists of five cards and are ranked, from
 * lowest to highest, in the following way:
 * <p>
 * High Card: Highest value card.
 * One Pair: Two cards of the same value.
 * Two Pairs: Two different pairs.
 * Three of a Kind: Three cards of the same value.
 * Straight: All cards are consecutive values.
 * Flush: All cards of the same suit.
 * Full House: Three of a kind and a pair.
 * Four of a Kind: Four cards of the same value.
 * Straight Flush: All cards are consecutive values of same suit.
 * Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
 * <p>
 * The cards are valued in the order:
 * 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
 * <p>
 * If two players have the same ranked hands then the rank made up of the
 * highest value wins; for example, a pair of eights beats a pair of fives
 * (see example 1 below). But if two ranks tie, for example, both players
 * have a pair of queens, then highest cards in each hand are compared (see
 * example 4 below); if the highest cards tie then the next highest cards are
 * compared, and so on.
 * <p>
 * Consider the following five hands dealt to two players:
 * Hand	 	Player 1	 	Player 2	 	Winner
 * 1	 	5H 5C 6S 7S KD
 * Pair of Fives
 * 2C 3S 8S 8D TD
 * Pair of Eights
 * Player 2
 * 2	 	5D 8C 9S JS AC
 * Highest card Ace
 * 2C 5C 7D 8S QH
 * Highest card Queen
 * Player 1
 * 3	 	2D 9C AS AH AC
 * Three Aces
 * 3D 6D 7D TD QD
 * Flush with Diamonds
 * Player 2
 * 4	 	4D 6S 9H QH QC
 * Pair of Queens
 * Highest card Nine
 * 3D 6D 7H QD QS
 * Pair of Queens
 * Highest card Seven
 * Player 1
 * 5	 	2H 2D 4C 4D 4S
 * Full House
 * With Three Fours
 * 3C 3D 3S 9S 9D
 * Full House
 * with Three Threes
 * Player 1
 * <p>
 * The file, poker.txt, contains one-thousand random hands dealt to two
 * players. Each line of the file contains ten cards (separated by a single
 * space): the first five are Player 1's cards and the last five are Player
 * 2's cards. You can assume that all hands are valid (no invalid characters
 * or repeated cards), each player's hand is in no specific order, and in
 * each hand there is a clear winner.
 * <p>
 * How many hands does Player 1 win?
 */
public class pe54j {

  public static void main(String[] args) throws Exception {
    long result = new BufferedReader(new InputStreamReader(
        pe54j.class.getResourceAsStream("poker.txt")))
        .lines()
        .filter(pe54j::playerOneWins)
        .count();

    System.out.println("Player 1 wins " + result + " times.");
  }

  static boolean playerOneWins(String bothHands) {
    List<Card> round = Stream.of(bothHands.trim().split("\\s+"))
                             .map(Card::new)
                             .collect(Collectors.toList());

    Hand plr1 = new Hand(round.subList(0, 5));
    Hand plr2 = new Hand(round.subList(5, 10));

    boolean player1Winner = plr1.compareTo(plr2) < 0;

    System.out.println(round.subList(0, 5) +
                           " " + plr1.bestHand + "  vs.  " + plr2.bestHand +
                           " " + round.subList(5, 10) +
                           (player1Winner ? " player 1 wins" : ""));

    return player1Winner;
  }


  enum SUIT {H, D, S, C}

  enum RANK {
    _2, _3, _4, _5, _6, _7, _8, _9, _T, _J, _Q, _K, _A;

    static RANK findRank(char rank) {
      return RANK.valueOf("_" + rank);
    }
  }

  enum Hands {
    HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_KIND, STRAIGHT, FLUSH, FULL_HOUSE,
    FOUR_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH
  }


  static class Hand implements Comparable<Hand> {

    final List<Card> cards;

    final Comparator<Card> byRank =
        Comparator.comparing((Card card) -> card.rank);

    Hands bestHand;

    Hand(List<Card> cards) {
      final int MIDDLE = 2;
      this.cards = cards;
      this.cards.sort(byRank.reversed()); // sort highest to lowest

      // Classify hands from best to worst
      // Detecting flushes and straights are easy.
      if (isFlush()) {
        bestHand = Hands.FLUSH;
        if (isStraight()) {
          bestHand = Hands.STRAIGHT_FLUSH;
          if (isAceHigh()) {
            bestHand = Hands.ROYAL_FLUSH;
          }
        }
      } else if (isStraight()) {
        bestHand = Hands.STRAIGHT;
      } else {
        // a histogram of rank frequencies.
        Map<RANK, List<Card>> map =
            cards.stream().collect(Collectors.groupingBy(o -> o.rank));

        this.bestHand = Hands.HIGH_CARD;  // default high card.

        if (map.size() == 2) { // Full house or four of a kind
          // probe map that a three of a kind exists in there somewhere
          if (map.values().stream().anyMatch(list -> list.size() == 3)) {
            bestHand = Hands.FULL_HOUSE;
          } else {
            bestHand = Hands.FOUR_KIND;
          }
        } else if (map.get(cards.get(MIDDLE).rank).size() == 3) {
          // middle card has two others this must be three of kind
          bestHand = Hands.THREE_KIND;
        } else if (map.size() == 3) {
          // after checking 3 of kind, only a map with three ranks is two pair
          bestHand = Hands.TWO_PAIR;
        } else if (map.size() == 4) { // a lonely pair
          bestHand = Hands.ONE_PAIR;
        }

        cards.sort(byRankOccurrenceThenRank(map).reversed());
      }
    }

    boolean isFlush() {
      return cards.stream()
                  .map(c -> c.suit)
                  .distinct().count() == 1;
    }

    boolean isStraight() {
      ListIterator<Card> iter = cards.listIterator();
      Card last = iter.next();
      while (iter.hasNext()) {
        Card next = iter.next();
        if (last.rank.ordinal() != next.rank.ordinal() + 1) {
          return false;
        }
        last = next;
      }
      return true;
    }

    boolean isAceHigh() {
      return cards.stream().max(Card::compareTo)
                  .map(card -> card.rank)
                  .get()
                  .equals(RANK._A);
    }

    Comparator<Card> byRankOccurrenceThenRank(Map<RANK, List<Card>> map) {
      return Comparator.comparing((Card card) -> map.get(card.rank).size())
                       .thenComparing(c -> c.rank);
    }

    @Override
    public String toString() {
      return cards.toString();
    }


    @Override
    public int compareTo(Hand o) {
      if (this.bestHand.equals(o.bestHand)) {
        Iterator<Card> iter = cards.iterator();
        Iterator<Card> oter = o.cards.iterator();
        Card dis = iter.next();
        Card dat = oter.next();
        // spin up to
        while (dis.compareTo(dat) == 0) {
          dis = iter.next();
          dat = iter.next();
        }
        return dat.rank.ordinal() - dis.rank.ordinal();
      }
      return o.bestHand.ordinal() - this.bestHand.ordinal();
    }

  }

  static class Card implements Comparable<Card> {

    final SUIT suit;

    final RANK rank;

    Card(String card) {
      assert card.length() == 2;
      this.rank = RANK.findRank(card.charAt(0));
      this.suit = SUIT.valueOf("" + card.charAt(1));
    }

    @Override
    public String toString() {
      return rank.name() + suit.name();
    }

    @Override
    public int compareTo(Card o) {
      return rank.compareTo(o.rank);
    }
  }
}
