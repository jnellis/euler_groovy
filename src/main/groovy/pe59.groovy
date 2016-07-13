/**
 * User: Joe Nellis
 * Date: 7/11/2016 
 * Time: 3:57 PM
 *
 *
 *

 Each character on a computer is assigned a unique code and the preferred standard is ASCII
 (American Standard Code for Information Interchange). For example, uppercase A = 65, asterisk
 (*) = 42, and lowercase k = 107.

 A modern encryption method is to take a text file, convert the bytes to ASCII, then XOR each
 byte with a given value, taken from a secret key. The advantage with the XOR function is that
 using the same encryption key on the cipher text, restores the plain text; for example, 65 XOR
 42 = 107, then 107 XOR 42 = 65.

 For unbreakable encryption, the key is the same length as the plain text message, and the key is
 made up of random bytes. The user would keep the encrypted message and the encryption key in
 different locations, and without both "halves", it is impossible to decrypt the message.

 Unfortunately, this method is impractical for most users, so the modified method is to use a
 password as a key. If the password is shorter than the message, which is likely, the key is
 repeated cyclically throughout the message. The balance for this method is using a sufficiently
 long password key for security, but short enough to be memorable.

 Your task has been made easy, as the encryption key consists of three lower case characters.
 Using cipher.txt (right click and 'Save Link/Target As...'), a file containing the encrypted
 ASCII codes, and the knowledge that the plain text must contain common English words, decrypt
 the message and find the sum of the ASCII values in the original text.

 */

encrypted = new File("cipher.txt").text.split(/\D/)
                                  .collect { Integer.parseInt(it) }

lowercase = 'a'..'z'
combinations = [lowercase, lowercase, lowercase].combinations()

def generateKey(seed) {   // expands key to size of encrypted message plus a little more possibly
  seed * (encrypted.size() / 3 + 1)
}

// priority queue that kicks out the decrypted string with the most spaces.
mostSpaces = new PriorityQueue<String>(
    Comparator.comparing({ str -> str.split(" ").size() }, Comparator.reverseOrder()))

combinations.each { seed ->
  def keyIter = generateKey(seed).iterator()
  def decrypted = encrypted.inject(new StringBuilder(), { result, int coded ->
    char decoded = (char) (coded ^ (int) keyIter.next())
    result.append(decoded)
  })

  mostSpaces.add(decrypted.toString())

}

// just a guess but was right, filter by decrypted message with most spaces (word divisions)
solution = mostSpaces.poll()
println solution
println(solution.collect { chr ->
  (int) chr
}.sum())






























