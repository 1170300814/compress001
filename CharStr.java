package compression;

public class CharStr {
  private String element;
  private double low;
  private double high;
  private double range;


  public CharStr(String ele, double low, double high, double range) {
    this.element = ele;
    this.low = low;
    this.high = high;
    this.range = range;
  }

  public CharStr() {

  }

  public void setelement(String ele) {
    this.element = ele;
  }

  public void setHigh(double high) {
    this.high = high;
  }

  public void setLow(double low) {
    this.low = low;
  }

  public void setRange(double range) {
    this.range = range;
  }

  public double getHigh() {
    return high;
  }

  public double getLow() {
    return low;
  }

  public double getRange() {
    return range;
  }

  public String getelement() {
    return element;
  }

  @Override
  public String toString() {
    return "CharStr[" + "cha='" + element + '\'' + ", low=" + low + ", high=" + high + ", range="
        + range + ']';
  }
}
