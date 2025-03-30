package zest;


import org.junit.jupiter.api.Test;

public class StringTransformerTest {
  @Test
  public void sameString() {
    String start = "abc";
    String target = "abc";
    assert StringTransformer.minOperations(start, target) == 0;
  }

  @Test
  public void emptyStart() {
    String start = "";
    String target = "abc";
    assert StringTransformer.minOperations(start, target) == 3;
  }

  @Test
  public void emptyTarget() {
    String start = "abc";
    String target = "";
    assert StringTransformer.minOperations(start, target) == 3;
  }

  @Test
  public void differentStringsOfSameLength() {
    String start = "abc";
    String target = "def";
    assert StringTransformer.minOperations(start, target) == 3;
  }

  @Test
  public void differentStringsOfDifferentLength() {
    String start = "abc";
    String target = "defg";
    assert StringTransformer.minOperations(start, target) == 4;
  }

  @Test
  public void nullStart() {
    String start = null;
    String target = "abc";
    assert StringTransformer.minOperations(start, target) == -1;
  }

  @Test
  public void nullTarget() {
    String start = "abc";
    String target = null;
    assert StringTransformer.minOperations(start, target) == -1;
  }

  @Test
  public void nullStartAndTarget() {
    String start = null;
    String target = null;
    assert StringTransformer.minOperations(start, target) == -1;
  }

  @Test
  public void insertOperation() {
    String start = "abc";
    String target = "abcd";
    assert StringTransformer.minOperations(start, target) == 1;
  }

  @Test
  public void removeOperation() {
    String start = "abcd";
    String target = "abc";
    assert StringTransformer.minOperations(start, target) == 1;
  }

  @Test
  public void replaceOperation() {
    String start = "abc";
    String target = "adc";
    assert StringTransformer.minOperations(start, target) == 1;
  }

  @Test
  public void multipleOperations() {
    String start = "abc";
    String target = "def";
    assert StringTransformer.minOperations(start, target) == 3;
  }

  @Test
  public void caseSensitive() {
    String start = "abc";
    String target = "ABC";
    assert StringTransformer.minOperations(start, target) == 3;
  }

}
