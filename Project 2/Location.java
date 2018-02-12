/* CMPT 435
 * Project 2 - Shortest-path word-melt solver
 * Filename: Location.java
 * Student name: Hannah Riedman
 *
 * Location class for the Word-Melt Solver.
 * Modifed code from Professor Rivas.
 */
 import java.util.Scanner;

class Location {
  final int CHANGE_LETTER = 0;
  final int INSERT_LETTER = 1;
  final int DELETE_LETTER = 2;
  final int DONE          = 3;

  String word;
  int iterationMode;
  int indexToChange;
  char nextLetter;

  Location() {
    word = "";
    indexToChange = 0;
    nextLetter = 'a';
    iterationMode = DONE;
  }
  /**
   * start method
   * This will set the iterationMode variable to CHANGE_LETTER
   * to initialize it.
   */
  void start() {
    iterationMode = CHANGE_LETTER;
  }

  Location nextNeighbor() {
    Location nextWord = new Location();
    nextWord.word = word;
    nextWord.indexToChange = indexToChange;
    nextWord.nextLetter = nextLetter;
    StringBuilder strBuilder = new StringBuilder(word);
    System.out.println("word: "+word);

    switch(iterationMode){
      case CHANGE_LETTER:
        if (nextLetter == 'z') {
          strBuilder.setCharAt(indexToChange,nextLetter);
          word = strBuilder.toString();
          nextLetter = 'a';
          indexToChange++;
          if (indexToChange == word.length()) {
            iterationMode++;
            nextLetter = 'a';
            indexToChange = 0;
            break;
          } else {
            break;
          }
        } else {
          strBuilder.setCharAt(indexToChange,nextLetter);
          word = strBuilder.toString();
          nextLetter++;
          break;
        }
      case INSERT_LETTER:
        if (nextLetter == 'z') {
          strBuilder.insert(indexToChange,nextLetter);
          word = strBuilder.toString();
          nextLetter = 'a';
          indexToChange++;
          if (indexToChange > word.length()) {
            iterationMode++;
            nextLetter = 'a';
            indexToChange = 0;
            break;
          } else {
            break;
          }
        } else {
          strBuilder.insert(indexToChange,nextLetter);
          word = strBuilder.toString();
          nextLetter++;
          break;
        }
      case DELETE_LETTER:
        /**if (nextLetter == 'z') {
          strBuilder.deleteCharAt(indexToChange);
          word = strBuilder.toString();
          nextLetter = 'a';
          indexToChange++;
          if (indexToChange == word.length()) {
            iterationMode++;
            nextLetter = 'a';
            indexToChange = 0;
            break;
          } else {
            break;
          }
        } else {
          strBuilder.deleteCharAt(indexToChange);
          word = strBuilder.toString();
          nextLetter++;
          break;
        }*/
        break;
      case DONE:
        break;
      default:
        break;
    }
    nextWord.word = word;
    nextWord.indexToChange = indexToChange;
    nextWord.nextLetter = nextLetter;
    nextWord.iterationMode = iterationMode;
    return nextWord;
  }
  /**
   * isDone method
   * @return boolean will be true if iterationMode is done and
   * we have reached all the options of the word.
   */
  boolean isDone() {
    return iterationMode == DONE;
  }
  /**
   * isEqual method
   * @param loc location that will check if it is eaual to this word
   * @return boolean will be true if word matches.
   */
  boolean isEqual(Location loc) {
    if (word.equals(loc.word)) {
      return true;
    } else {
      return false;
    }
  }
  /**
   * SteramOut method
   * This wil print out the word for the location.
   */
  void streamOut() {
    System.out.println(word);
  }
  /**
   * streamIn method
   * This will set the string on the first line
   * as the word for the location.
   * @param input from the scanner
   */
  void streamIn(Scanner input) {
    if (input.hasNext()) {
      word = input.nextLine();
    }
  }
 /**
  * isLess Method
  * @param loc location is a location that will be compared.
  * @return boolean that will be true if the location is less
  * then the passed in location loc.
  */
  boolean isLess(Location loc) {
    String loc1 = this.word;
    String loc2 = loc.word;
    //System.out.println(loc1.compareTo(loc2));
    if ((loc1.compareTo(loc2)) <= -1) {
      return true;
    } else {
      return false;
    }
  }
}
