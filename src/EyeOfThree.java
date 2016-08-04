/**
 * Created by kevin on 8/4/16.
 */
public class EyeOfThree {

    public static void main(String[] args) {
        String folder = args[0];
        String file = args[1];
        String flags = args[3];

        String width = flags.substring(0, flags.indexOf("x"));
        flags = flags.substring(width.length()+1, flags.length());
        String height = flags.substring(0, flags.indexOf("+"));
        flags = flags.substring(height.length()+1, flags.length());
        String xpos = flags.substring(0, flags.indexOf("+"));
        flags = flags.substring(xpos.length()+1, flags.length());
        String ypos = flags;

        int _width = Integer.valueOf(width);
        int _height = Integer.valueOf(height);
        int _xpos = Integer.valueOf(xpos);
        int _ypos = Integer.valueOf(ypos);


        ImageWindow window = new ImageWindow(folder, file, _width, _height, _xpos, _ypos);
    }

}
