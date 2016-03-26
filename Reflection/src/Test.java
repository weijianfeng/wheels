public class Test {

    private int inta;
    public String stra = "hehe";
    public String strb;
    protected int intb = 1;

    public Test() {

    }

    public Test(int inta, String stra, String strb) {
        super();
        this.inta = inta;
        this.stra = stra;
        this.strb = strb;
    }

    public int getInta() {
        return inta;
    }

    public void setInta(int inta) {
        this.inta = inta;
    }

    public String getStra() {
        return stra;
    }

    public void setStra(String stra) {
        this.stra = stra;
    }

    public String getStrb() {
        return strb;
    }

    public void setStrb(String strb) {
        this.strb = strb;
    }

    public void hello() {
        System.out.println("hello");
    }

    public int showint(int a) {
        return a;
    }

    private String showstr(String a) {
        return a;
    }

    protected String showstr(String a, String b) {
        return a + b;
    }

}
