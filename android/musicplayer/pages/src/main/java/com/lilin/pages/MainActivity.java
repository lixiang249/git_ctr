package com.lilin.pages;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener,MediaPlayer.OnErrorListener,SeekBar.OnSeekBarChangeListener,AdapterView.OnItemClickListener,Runnable{

    /***************************************/
     Button btn_last,btn_start,btn_next,btn_jmp_list;
     ImageView iv_background;
     RoundImageView iv_circle;
     TextView tv;
     Bitmap bm_origin,bm_blured;
     Animation mAnimation;
     SeekBar seekBar;
     //public MediaPlayer mPlayer;
     public static final String MUSIC_PATH=new String("/mnt/shell/emulated/0/Music");
     //ArrayList<String> mMusicList=new ArrayList<String>();
     ListView lv_musicList;
     int currentListitem=0;
    protected static final int SEARCH_MUSIC_SUCCESS = 0;// 搜索成功标记
    ListView listView;
    Button btnPlay;
    TextView tv_currTime,tv_totalTime,tv_showName;
    public static List<String> list;
    ProgressDialog pd; // 进度条对话框
    public MusicListAdapter ma;// 适配器
    public MediaPlayer mp;
    int currIndex = 0;// 表示当前播放的音乐索引
    boolean flag = true;//控制进度条线程标记

    // 定义当前播放器的状态״̬
    private static final int IDLE = 0;
    private static final int PAUSE = 1;
    private static final int START = 2;
    private static final int CURR_TIME_VALUE = 1;

    private int currState = IDLE; // 当前播放器的状态
    //定义线程池（同时只能有一个线程运行）
    ExecutorService es = Executors.newSingleThreadExecutor();


     /******************************************/

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        //mPlayer=new MediaPlayer();
        //findView();

        //musicList();

        //setListener();

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});
        Toast.makeText(this, "初始化", Toast.LENGTH_LONG).show();
        list = new ArrayList<String>();
        mp = new MediaPlayer();
        mp.setOnCompletionListener(this);
        mp.setOnErrorListener(this);
        initView();
    }

    protected void onDestroy() {
        if (mp != null) {
            mp.stop();
            flag= false;
            //释放资源
            mp.release();
        }
        super.onDestroy();
    }
/**********************************************************/
    private void initView() {
        btnPlay = (Button) findViewById(R.id.btn_start);
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        //assert seekBar != null;
        //seekBar.setOnSeekBarChangeListener(this);
        //listView = (ListView) findViewById(R.id.lv_musiclist);
        //FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.fab);
        //assert fab != null;
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Snackbar.make(v, "刷新列表", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //        list.clear();
        //        //是否有外部存储设备
        //        if (Environment.getExternalStorageState().equals(
        //                Environment.MEDIA_MOUNTED)) {
        //            pd = ProgressDialog.show(MainActivity.this, "", "正在搜索音乐文件...", true);
        //            new Thread(new Runnable() {
        //                String[] ext = { ".mp3" };
        //                File file = Environment.getExternalStorageDirectory();

        //                public void run() {
        //                    search(file, ext);
        //                    hander.sendEmptyMessage(SEARCH_MUSIC_SUCCESS);
        //                }
        //            }).start();

        //        } else {
        //            Toast.makeText(MainActivity.this, "请插入外部存储设备..", Toast.LENGTH_LONG).show();
        //        }
        //    }
        //});
        //listView.setOnItemClickListener(this);
        tv_currTime = (TextView) findViewById(R.id.textView1_curr_time);
        tv_totalTime = (TextView) findViewById(R.id.textView1_total_time);
        tv_showName = (TextView) findViewById(R.id.tv_music_name);
    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    //    //从xml文件中装载菜单
    //    getMenuInflater().inflate(R.menu.menu_bar, menu);
    //    return super.onCreateOptionsMenu(menu);
    //}

    private Handler hander = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SEARCH_MUSIC_SUCCESS:
                    //搜索音乐文件结束时
                    ma = new MusicListAdapter();
                    listView.setAdapter( ma);
                    pd.dismiss();
                    break;
                case CURR_TIME_VALUE:
                    //设置当前时间
                    tv_currTime.setText(msg.obj.toString());
                    break;
                default:
                    break;
            }
        };
    };

    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
    //    return false;
    //}

    // 搜索音乐文件
    private List<String> search(File file, String[] ext) {
        if (file != null) {
            if (file.isDirectory()) {
                File[] listFile = file.listFiles();
                if (listFile != null) {
                    for (int i = 0; i < listFile.length; i++) {
                        search(listFile[i], ext);
                    }
                }
            } else {
                String filename = file.getAbsolutePath();
                for (int i = 0; i < ext.length; i++) {
                    if (filename.endsWith(ext[i])) {
                        list.add(filename);
                        break;
                    }
                }
            }
        }
        return list;
    }

    class MusicListAdapter extends BaseAdapter {

        public int getCount() {
            return list.size();
        }

        public Object getItem(int position) {
            return list.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.music_item,
                        null);
            }
            TextView tv_music_name = (TextView) convertView
                    .findViewById(R.id.tv_music_name);
            TextView tv_music_path = (TextView) convertView
                    .findViewById(R.id.tv_music_path);
            //ImageView iv_music_image= (ImageView) findViewById(R.id.iv_music_image);
            tv_music_name.setText(new File(list.get(position)).getName());
            tv_music_path.setText(list.get(position));
            //iv_music_image.setBackgroundColor(getColor(R.color.colorAccent));
            //tv_music_name.setText(list.get(position));
            return convertView;
        }

        public void notifyDataSetChanged() {
        }
    }


    private void play() {
        switch (currState) {
            case IDLE:
                start();
                break;
            case PAUSE:
                mp.pause();
                //btnPlay.setImageResource(R.drawable.ic_media_play);
                currState = START;
                break;
            case START:
                mp.start();
                //btnPlay.setImageResource(R.drawable.ic_media_pause);
                currState = PAUSE;
        }
    }

    //上一首
    private void previous() {
        if((currIndex-1)>=0){
            currIndex--;
            start();
        }else{
            Toast.makeText(this, "当前已经是第一首歌曲了", Toast.LENGTH_SHORT).show();
        }
    }

    //下一自首
    private void next() {
        if(currIndex+1<list.size()){
            currIndex++;
            start();
        }else{
            Toast.makeText(this, "当前已经是最后一首歌曲了", Toast.LENGTH_SHORT).show();
        }
    }

    //开始播放
    private void start() {
        if (list.size() > 0 && currIndex < list.size()) {
            String SongPath = list.get(currIndex);
            mp.reset();
            try {
                mp.setDataSource(SongPath);
                mp.prepare();
                mp.start();
                initSeekBar();
                es.execute(this);
                tv_showName.setText(list.get(currIndex));
                //btnPlay.setImageResource(R.drawable.ic_media_pause);
                currState = PAUSE;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "播放列表为空", Toast.LENGTH_SHORT).show();
        }
    }

    //播放按钮
    public void play(View v){
        play();
    }

    //上一首按钮
    public void previous(View v){
        previous();
    }

    //下一首按钮
    public void next(View v){
        next();
    }

    //监听器，当当前歌曲播放完时触发，播放下一首
    public void onCompletion(MediaPlayer mp) {
        if(list.size()>0){
            next();
        }else{
            Toast.makeText(this, "播放列表为空", Toast.LENGTH_SHORT).show();
        }
    }

    //当播放异常时触发
    public boolean onError(MediaPlayer mp, int what, int extra) {
        mp.reset();
        return false;
    }

    //初始化SeekBar
    private void initSeekBar(){
        seekBar.setMax(mp.getDuration());
        seekBar.setProgress(0);
        tv_totalTime.setText(toTime(mp.getDuration()));
    }

    private String toTime(int time){
        int minute = time / 1000 / 60;
        int s = time / 1000 % 60;
        String mm = null;
        String ss = null;
        if(minute<10)mm = "0" + minute;
        else mm = minute + "";

        if(s <10)ss = "0" + s;
        else ss = "" + s;

        return mm + ":" + ss;
    }

    public void run() {
        flag = true;
        while(flag){
            if(mp.getCurrentPosition()<seekBar.getMax()){
                seekBar.setProgress(mp.getCurrentPosition());
                Message msg = hander.obtainMessage(CURR_TIME_VALUE, toTime(mp.getCurrentPosition()));
                hander.sendMessage(msg);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                flag = false;
            }
        }
    }

    //SeekBar监听器
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        //是否由用户改变
        if(fromUser){
            mp.seekTo(progress);
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    //ListView监听器
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        currIndex = position;
        start();
    }





    public void findView(){
        iv_circle=(RoundImageView)findViewById(R.id.iv_circle);
        iv_background=(ImageView)findViewById(R.id.iv_background);
        btn_last= (Button) findViewById(R.id.btn_last);
        btn_start= (Button) findViewById(R.id.btn_start);
        btn_next= (Button) findViewById(R.id.btn_next);
        btn_jmp_list= (Button) findViewById(R.id.btn_jmp_list);

    }

    private void init() {
        bm_origin= BitmapFactory.decodeResource(getResources(),R.mipmap.sea_star);

        mAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_repeat);
        mAnimation.setInterpolator(new LinearInterpolator());
        iv_circle.startAnimation(mAnimation);

        bm_blured= FastBlurUtil.doBlur(bm_origin,8,false);
        iv_background.setImageBitmap(bm_blured);

    }

    //private void setListener() {
    //    btn_jmp_list.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //        public void onClick(View v) {

    //        }
    //    });

    //    btn_last.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //        public void onClick(View v) {

    //        }
    //    });

    //    btn_start.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //        public void onClick(View v) {

    //        }
    //    });

    //    btn_next.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //        public void onClick(View v) {

    //        }
    //    });

    //}

    //private void initSeekBar(){
    //    seekBar.setMax(mPlayer.getDuration());
    //    seekBar.setProgress(0);
    //    //tv_totalTime.setText(toTime(mp.getDuration()));
    //}

    //void musicList(){
    //    File home=new File(MUSIC_PATH);
    //    File[] f=home.listFiles(new MusicFilter());
    //    if(f.length>0){
    //        for (File file:f) {
    //            mMusicList.add(file.getName());
    //        }
    //        ArrayAdapter<String> musicList=new ArrayAdapter<String>(MainActivity.this,
    //                android.R.layout.simple_list_item_1,mMusicList);
    //        lv_musicList= (ListView) findViewById(R.id.lv_musiclist);
    //        lv_musicList.setAdapter(musicList);
    //    }

    //}
/***************************************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView;
            int nSection=getArguments().getInt(ARG_SECTION_NUMBER);
            if(nSection==1){

                rootView = inflater.inflate(R.layout.activity_image, container, false);
                RoundImageView iv_circle=(RoundImageView)rootView.findViewById(R.id.iv_circle);
                ImageView iv_background=(ImageView)rootView.findViewById(R.id.iv_background);
                Bitmap bm_origin= BitmapFactory.decodeResource(getResources(),R.mipmap.sea_star);
                Button btn_jmp_list= (Button) rootView.findViewById(R.id.btn_jmp_list);

                Animation mAnimation = AnimationUtils.loadAnimation(btn_jmp_list.getContext(), R.anim.rotate_repeat);
                mAnimation.setInterpolator(new LinearInterpolator());
                iv_circle.startAnimation(mAnimation);
                iv_background.setImageBitmap(FastBlurUtil.doBlur(bm_origin,8,false));


            }else if(nSection==2) {
                rootView = inflater.inflate(R.layout.activity_scrolling, container, false);
            }
            else{
                rootView = inflater.inflate(R.layout.fragment_main, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
            }
            return null;
        }
    }
}

class MusicFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String filename) {
        return (filename.endsWith(".mp3")||filename.endsWith(".mp4"));
    }
}
