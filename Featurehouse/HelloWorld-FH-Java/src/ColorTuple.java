import java.awt.Color; 

public  class  ColorTuple {
	
    private final Color awtColor;

	  // For GUI usage
    private final String ansiCode;

	 // For terminal usage

    public ColorTuple(Color awtColor, String ansiCode) {
        this.awtColor = awtColor;
        this.ansiCode = ansiCode;
    }

	

    public Color getAwtColor() {
        return awtColor;
    }

	

    public String getAnsiCode() {
        return ansiCode;
    }


}
