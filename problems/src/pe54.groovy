/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 10/21/13
 * Time: 5:59 PM

 Poker hands
 Problem 54

 In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:

 High Card: Highest value card.
 One Pair: Two cards of the same value.
 Two Pairs: Two different pairs.
 Three of a Kind: Three cards of the same value.
 Straight: All cards are consecutive values.
 Flush: All cards of the same suit.
 Full House: Three of a kind and a pair.
 Four of a Kind: Four cards of the same value.
 Straight Flush: All cards are consecutive values of same suit.
 Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.

 The cards are valued in the order:
 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.

 If two players have the same ranked hands then the rank made up of the highest
 value wins; for example, a pair of eights beats a pair of fives (see example 1
 below). But if two ranks tie, for example, both players have a pair of queens,
 then highest cards in each hand are compared (see example 4 below); if the
 highest cards tie then the next highest cards are compared, and so on.

 Consider the following five hands dealt to two players:
 Hand	 	Player 1	 	Player 2	 	Winner
 1	 	5H 5C 6S 7S KD
 Pair of Fives
 2C 3S 8S 8D TD
 Pair of Eights
 Player 2
 2	 	5D 8C 9S JS AC
 Highest card Ace
 2C 5C 7D 8S QH
 Highest card Queen
 Player 1
 3	 	2D 9C AS AH AC
 Three Aces
 3D 6D 7D TD QD
 Flush with Diamonds
 Player 2
 4	 	4D 6S 9H QH QC
 Pair of Queens
 Highest card Nine
 3D 6D 7H QD QS
 Pair of Queens
 Highest card Seven
 Player 1
 5	 	2H 2D 4C 4D 4S
 Full House
 With Three Fours
 3C 3D 3S 9S 9D
 Full House
 with Three Threes
 Player 1

 The file, poker.txt, contains one-thousand random hands dealt to two players.
 Each line of the file contains ten cards (separated by a single space): the
 first five are Player 1's cards and the last five are Player 2's cards. You
 can assume that all hands are valid (no invalid characters or repeated cards),
 each player's hand is in no specific order, and in each hand there is a clear
 winner.

 How many hands does Player 1 win?

 */


enum Hands {
  royalFlush(~/T([HDSC])J\1Q\1K\1A\1/, { [] }),

  fourOfAKind(~/(?:(\w).)?(\w).(?:\2.){3}(?:(\w).)?/, {
    [2, 3, 1]
  }),

  fullHouse(~/(?:(.).\1.\1.(\w).\2.)|(?:(.).\3.(\w).\4.\4.)/, {
    [1, 4, 2, 3]
  }),

  flush(~/(.)([HDSC])(.)\2(.)\2(.)\2(.)\2/, {
    [6, 5, 4, 3, 1]
  }),

  straight(~/(2.3.4.5.6.)|(3.4.5.6.7.)|(4.5.6.7.8.)|(5.6.7.8.9.)|(6.7.8.9.T.)|(7.8.9.T.J.)|(8.9.T.J.Q.)|(9.T.J.Q.K.)|(T.J.Q.K.A.)/, { matcher ->
    def result = []
    println matcher[0]
    def matchIndex = matcher[0].eachWithIndex { it, i ->
      println it
      if (it && i > 0) {
        result << i
        return
      }
    }
    println result
    result
  }),

  threeOfAKind(~/(?:[0-9AJKQT][HDSC])*?([0-9AJKQT]).\1.\1.(?:[0-9AJKQT][HDSC])*?/, {}),


  pair(~/(?:[0-9AJKQT][HDSC])*?([0-9AJKQT]).\1.(?:[0-9AJKQT][HDSC])*?/, {}),

  twoPairs(~/ (?:([0-9AJKQT])[HDSC])?([0-9AJKQT]).\2.(?:([0-9AJKQT])[HDSC])?([0-9AJKQT])[HDSC]\4.(?:([0-9AJKQT])[HDSC])? /,
      { [it[0][4], it[0][2], it[0][5], it[0][3], it[0][1]] }),

  highCard(~/ (?:(.).){5} /, { [it[0][5], it[0][4], it[0][3], it[0][2], it[0][1]] });



  public static final def suits = ['H', 'D', 'S', 'C']
  public static final def ranks = ['2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A']
  public static final def byRank = { a, b -> ranks.indexOf(a[0]) <=> ranks.indexOf(b[0]) } as Comparator
  public static final def g = { matcher, group -> ranks.indexOf(matcher[0][group]) }

  private final def regex
  private final def cardsToBeat

  Hands(def regex, def cardsToBeat) {
    this.regex = regex
    this.cardsToBeat = cardsToBeat
  }

  def matches(cards) {
    def result = []
    def m = this.regex.matcher(cards)
    if (m.matches()) {
      def secondary = cardsToBeat(m)
      secondary = secondary.findResults { g ->
        println m[0][g]
        m[0][g] ?
          ranks.indexOf(m[0][g]) + 2
        : null
      }
      result = [ordinal(), secondary].flatten()
    }
    println "result: $result"
    // return hand type, then secondary cards to compare if hand is same type.
    result
  }
}



assert Hands.royalFlush.matches("TDJDQDKDAD") == [Hands.royalFlush.ordinal()]
assert Hands.fourOfAKind.matches("3DJDJHJSJC") == [Hands.fourOfAKind.ordinal(), 11, 3]
assert Hands.fourOfAKind.matches("4D4H4S4C5C") == [Hands.fourOfAKind.ordinal(), 4, 5]
assert Hands.fullHouse.matches("3D3H5H5D5S") == [Hands.fullHouse.ordinal(), 5, 3]
assert Hands.fullHouse.matches("3D3H3C5D5S") == [Hands.fullHouse.ordinal(), 3, 5]
assert Hands.flush.matches("2H4H5HTHJH") == [Hands.flush.ordinal(), 11, 10, 5, 4, 2]
//assert Hands.straight.matches("2S3C4C5H6H") == [Hands.straight.ordinal(), 6, 5, 4, 3, 2]
assert Hands.straight.matches("TSJCQCKHAH") == [Hands.straight.ordinal(), 14, 13, 12, 11, 10]
assert Hands.threeOfAKind.matches("4S4H4C5H9C") == [Hands.threeOfAKind.ordinal()]
assert Hands.threeOfAKind.matches("2C4S4H4C5H") == [Hands.threeOfAKind.ordinal()]
assert Hands.threeOfAKind.matches("5H6C7S7H7C") == [Hands.threeOfAKind.ordinal()]
assert Hands.twoPairs.matches("4S4H5C5H9C") == [Hands.twoPairs.ordinal()]
assert Hands.twoPairs.matches("4H4C5S7H7C") == [Hands.twoPairs.ordinal()]
assert Hands.twoPairs.matches("2C4S4H5C5H") == [Hands.twoPairs.ordinal()]
assert Hands.pair.matches("4S4H5C7H9C") == [Hands.pair.ordinal()]
assert Hands.pair.matches("2C4S4H7C8H") == [Hands.pair.ordinal()]
assert Hands.pair.matches("2H3C4S4H7C") == [Hands.pair.ordinal()]
assert Hands.pair.matches("2C3S4H5C5H") == [Hands.pair.ordinal()]
assert Hands.highCard.matches("2C3S4H5C5H") == [Hands.highCard.ordinal()]


def player1wins = 0

new File('poker.txt').eachLine { line ->
  def playerHands = line.split()
  def players = [playerHands[0..<5], playerHands[5..<10]]
  def points = [0, 0]
  def message = ['', '']
  def handToBeat = Hands.highCard

  players.eachWithIndex { player, i ->
    def cards = player.sort(byRank).join()
    Hands hand = Hands.values().first()
    def cardsToBeat
    while (!cardsToBeat || hand != handToBeat) {
      hand++
      cardsToBeat = hand.matches(cards)
    }

    print "$hand $cardsToBeat\t"
//
//    switch (cards) {
//    // four of a kind
//      case fourOfAKind:
//        points[i] = 7 * 26
//        message[i] = "four of a kind"
//        break
//
//      case fullHouse:
//        points[i] = 6 * 26
//        message[i] = "full house"
//        break
//
//      case threeOfAKind:
//        points[i] = 3 * 26
//        message[i] = "three of a kind"
//        break
//
//      case twoPairs:
//        points[i] = 2 * 26
//        message[i] = "two pairs"
//        break
//
//      case pair:
//        points[i] = 26
//        message[i] = "one pair"
//        break
//
//      case flush:
//        points[i] = 5 * 26
//        message[i] = "flush"
//        break
//
//      case straight:
//        switch (cards) {
//          case royalFlush:
//            points[i] = 9 * 26
//            message[i] = "royal straight flush"
//            break
//          case flush:
//            points[i] = 8 * 26
//            message[i] = "straight flush"
//            break
//          default:
//            points[i] = 4 * 26
//            message[i] = "straight"
//        }
//        break
//
//      default:
//        points[i] += ranks.indexOf(cards[8]) + 2
//        message[i] = "high card ${cards[8]}"
//    }

    print "\tp${i + 1}\t$cards - ${message[i]}\t ${points[i]}\t"
  }
  println ""
}
