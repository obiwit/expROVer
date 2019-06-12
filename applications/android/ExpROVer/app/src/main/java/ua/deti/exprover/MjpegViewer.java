//package ua.deti.exprover;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.Window;
//import android.view.WindowManager;
//
//import ua.deti.exprover.utils.MjpegInputStream;
//import ua.deti.exprover.utils.MjpegView;
//
//public class MjpegViewer extends Activity {
//
//    private MjpegView mv;
//    private static final int MENU_QUIT = 1;
//
//    /* Creates the menu items */
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(0, MENU_QUIT, 0, "Quit");
//        return true;
//    }
//
//    /* Handles item selections */
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case MENU_QUIT:
//                finish();
//                return true;
//        }
//        return false;
//    }
//
//    public void onCreate(Bundle icicle) {
//        super.onCreate(icicle);
//        //sample public cam
//        String URL = "http://gamic.dnsalias.net:7001/img/video.mjpeg";
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//        mv = new MjpegView(this);
//        setContentView(mv);
//        mv.setSource(MjpegInputStream.read(URL));
//        mv.setDisplayMode(MjpegView.SIZE_BEST_FIT);
//        mv.showFps(false);
//    }
//
//    public void onPause() {
//        super.onPause();
//        mv.stopPlayback();
//    }
//}