package com.example.admin.weatherapp.view;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.weatherapp.R;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/10/19.
 */
public class Today24HourView extends View{
    private static final String TAG = "Today24HourView";

    private Context context;

    private static final int ITEM_SIZE = 24;  //24小时
    private static final int ITEM_WIDTH = 140; //每个Item的宽度
    private static final int MARGIN_LEFT_ITEM = 0; //左边预留宽度
    private static final int MARGIN_RIGHT_ITEM = 0; //右边预留宽度

    private static final int windyBoxAlpha = 80;
    private static final int windyBoxMaxHeight = 80;
    private static final int windyBoxMinHeight = 20;
    private static final int windyBoxSubHight = windyBoxMaxHeight - windyBoxMinHeight;
    private static final int bottomTextHeight = 60;

    private int mHeight, mWidth;
    private int tempBaseTop;  //温度折线的上边Y坐标
    private int tempBaseBottom; //温度折线的下边Y坐标
    private Paint bitmapPaint, windyBoxPaint, linePaint, pointPaint, dashLinePaint;
    private TextPaint textPaint;

    private List<HourItem> listItems;
    private int maxScrollOffset = 0;//滚动条最长滚动距离
    private int scrollOffset = 0; //滚动条偏移量
    private int currentItemIndex = 0; //当前滚动的位置所对应的item下标
    private int currentWeatherRes = -1;

    private int maxTemp = 26;
    private int minTemp = 21;
    private int maxWindy = 5;
    private int minWindy = 2;

    /**
     * 气温
     * */
    private static  int TEMP[] = {22, 23, 23, 23, 23,
            22, 23, 23, 23, 22,
            21, 21, 22, 22, 23,
            23, 24, 24, 25, 25,
            25, 26, 25, 24};

    /**
     * 风力
     * */
    private static  int WINDY[] =
            {2, 2, 3, 3, 3,
                    4, 4, 4, 3, 3,
                    3, 4, 4, 4, 4,
                    2, 2, 2, 3, 3,
                    3, 5, 5, 5};

    /**
     * 天气资源图片
     * */
    private static  int WEATHER_RES[] ={-1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,
            -1,-1,-1,-1};
    private static int TIME_FROM;

    public void setData(int temp[],  int weatherRes[], int timeFrom){
        TEMP = temp;

        TIME_FROM = timeFrom;

        WEATHER_RES[0] = weatherRes[0];
        for (int i = 1 ; i < weatherRes.length ; i ++){
            if (weatherRes[i]  == weatherRes[i-1]){
                WEATHER_RES[i] = -1;
            }else {
                WEATHER_RES[i] = weatherRes[i];
            }
        }

        maxTemp = TEMP[0];
        minTemp = TEMP[0];
        for (int t: TEMP){
            if (t < minTemp){
                minTemp = t;
            }
            if ( t > maxTemp){
                maxTemp = t;
            }
        }
        init();
        this.invalidate();
    }


    public Today24HourView(Context context) {
        this(context, null);
    }

    public Today24HourView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Today24HourView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();

    }

    private void init() {
        mWidth = MARGIN_LEFT_ITEM + MARGIN_RIGHT_ITEM + ITEM_SIZE * ITEM_WIDTH;
        mHeight = 500; //暂时先写死
        tempBaseTop = (500 - bottomTextHeight)/4;
        tempBaseBottom = (500 - bottomTextHeight)*2/3;

        initHourItems();
        initPaint();
    }

    private void initPaint() {
        pointPaint = new Paint();
        pointPaint.setColor(new Color().LTGRAY);
        pointPaint.setAntiAlias(true);
        pointPaint.setTextSize(1);

        linePaint = new Paint();
        linePaint.setColor(new Color().BLUE);
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(3);

        dashLinePaint = new Paint();
        dashLinePaint.setColor(new Color().GRAY);
        PathEffect effect = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
        dashLinePaint.setPathEffect(effect);
        dashLinePaint.setStrokeWidth(3);
        dashLinePaint.setAntiAlias(true);
        dashLinePaint.setStyle(Paint.Style.STROKE);

        windyBoxPaint = new Paint();
        windyBoxPaint.setTextSize(1);
        windyBoxPaint.setColor(Color.parseColor("#7da6dc"));
        windyBoxPaint.setAlpha(windyBoxAlpha);
        windyBoxPaint.setAntiAlias(true);

        textPaint = new TextPaint();
        textPaint.setTextSize(DisplayUtil.sp2px(getContext(), 13));
        textPaint.setColor(new Color().DKGRAY);
        textPaint.setAntiAlias(true);

        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
    }

    //简单初始化下，后续改为由外部传入
    private void initHourItems(){
        listItems = new ArrayList<>();
        for(int i=0; i<ITEM_SIZE; i++){
            String time;
            int j = (i + TIME_FROM) % 24;
            if(j<10){
                time = "0" + j + ":00";
            } else {
                time = j + ":00";
            }
            int left =MARGIN_LEFT_ITEM  +  i * ITEM_WIDTH;
            int right = left + ITEM_WIDTH - 1;
            int top = (int)(mHeight -bottomTextHeight +
                    (maxWindy - WINDY[i])*1.0/(maxWindy - minWindy)*windyBoxSubHight
                    - windyBoxMaxHeight);
            int bottom =  mHeight - bottomTextHeight;
            Rect rect = new Rect(left, top, right, bottom);
            Point point = calculateTempPoint(left, right, TEMP[i]);

            HourItem hourItem = new HourItem();
            hourItem.windyBoxRect = rect;
            hourItem.time = time;
            hourItem.windy = WINDY[i];
            hourItem.temperature = TEMP[i];
            hourItem.tempPoint = point;
            hourItem.res = WEATHER_RES[i];
            listItems.add(hourItem);
        }
    }

    private Point calculateTempPoint(int left, int right, int temp){
        double minHeight = tempBaseTop;
        double maxHeight = tempBaseBottom;
        double tempY = maxHeight - (temp - minTemp)* 1.0/(maxTemp - minTemp) * (maxHeight - minHeight);
        Point point = new Point((left + right)/2, (int)tempY);
        return point;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0; i<listItems.size(); i++){
            Rect rect = listItems.get(i).windyBoxRect;
            Point point = listItems.get(i).tempPoint;
            //画风力的box和提示文字
            onDrawBox(canvas, rect, i);
            //画温度的点
            onDrawTemp(canvas, i);
            //画表示天气图片
            if(listItems.get(i).res != -1 && i != currentItemIndex){
//                Drawable drawable = ContextCompat.getDrawable(getContext(), listItems.get(i).res);
                Drawable drawable = null;
                try {
                    drawable = getImageDrawableFromAssets( listItems.get(i).res + ".png");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                drawable.setBounds(point.x - DisplayUtil.dip2px(getContext(), 10) - 10,
                        point.y - DisplayUtil.dip2px(getContext(), 25) - 10,
                        point.x + DisplayUtil.dip2px(getContext(), 10) + 10,
                        point.y - DisplayUtil.dip2px(getContext(), 5) + 10);
                drawable.draw(canvas);
            }
            onDrawLine(canvas, i);
            onDrawText(canvas, i);
        }
        //底部水平的白线
        linePaint.setColor(Color.parseColor("#ffffff"));

        canvas.drawLine(0, mHeight - bottomTextHeight, mWidth, mHeight - bottomTextHeight, linePaint);
        //中间温度的虚线
//        Path path1 = new Path();
//        path1.moveTo(MARGIN_LEFT_ITEM, tempBaseTop);
//        path1.quadTo(mWidth - MARGIN_RIGHT_ITEM, tempBaseTop, mWidth - MARGIN_RIGHT_ITEM, tempBaseTop);
//        canvas.drawPath(path1, dashLinePaint);
//        Path path2 = new Path();
//        path2.moveTo(MARGIN_LEFT_ITEM, tempBaseBottom);
//        path2.quadTo(mWidth - MARGIN_RIGHT_ITEM, tempBaseBottom, mWidth - MARGIN_RIGHT_ITEM, tempBaseBottom);
//        canvas.drawPath(path2, dashLinePaint);
    }

    private void onDrawTemp(Canvas canvas, int i) {
        HourItem item = listItems.get(i);
        Point point = item.tempPoint;
        canvas.drawCircle(point.x, point.y, 10, pointPaint);

        if(currentItemIndex == i) {
            //计算提示文字的运动轨迹
            int Y = getTempBarY();
            //画出背景图片
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.icon_hour_24_float);
            drawable.setBounds(getScrollBarX(),
                    Y - DisplayUtil.dip2px(getContext(), 24),
                    getScrollBarX() + ITEM_WIDTH,
                    Y - DisplayUtil.dip2px(getContext(), 4));
            drawable.draw(canvas);
            //画天气
            int res = findCurrentRes(i);
            if(res != -1) {
//                Drawable drawTemp = ContextCompat.getDrawable(getContext(), res);
                Drawable drawTemp = null;
                try {
                    drawTemp = getImageDrawableFromAssets(res + ".png");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                drawTemp.setBounds(getScrollBarX()+ITEM_WIDTH/2 + (ITEM_WIDTH/2 - DisplayUtil.dip2px(getContext(), 18))/2 - 10,
                        Y - DisplayUtil.dip2px(getContext(), 23) - 10,
                        getScrollBarX()+ITEM_WIDTH - (ITEM_WIDTH/2 - DisplayUtil.dip2px(getContext(), 18))/2 + 10,
                        Y - DisplayUtil.dip2px(getContext(), 5) + 10 ) ;
                drawTemp.draw(canvas);

            }
            //画出温度提示
            int offset = ITEM_WIDTH/2;
            if(res == -1)
                offset = ITEM_WIDTH;
            Rect targetRect = new Rect(getScrollBarX(), Y - DisplayUtil.dip2px(getContext(), 24)
                    , getScrollBarX() + offset, Y - DisplayUtil.dip2px(getContext(), 4));
            Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
            int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(item.temperature + "°", targetRect.centerX(), baseline, textPaint);
        }
    }

    private int findCurrentRes(int i) {
        if(listItems.get(i).res != -1)
            return listItems.get(i).res;
        for(int k=i; k>=0; k--){
            if(listItems.get(k).res != -1)
                return listItems.get(k).res;
        }
        return -1;
    }

    //画底部风力的BOX
    private void onDrawBox(Canvas canvas, Rect rect, int i) {
        // 新建一个矩形
        RectF boxRect = new RectF(rect);
        HourItem item = listItems.get(i);
        if(i == currentItemIndex) {
            windyBoxPaint.setAlpha(255);
            canvas.drawRoundRect(boxRect, 4, 4, windyBoxPaint);
            //画出box上面的风力提示文字
            Rect targetRect = new Rect(getScrollBarX(), rect.top - DisplayUtil.dip2px(getContext(), 20)
                    , getScrollBarX() + ITEM_WIDTH, rect.top - DisplayUtil.dip2px(getContext(), 0));
            Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
            int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("风力" + item.windy + "级", targetRect.centerX(), baseline, textPaint);
        } else {
            windyBoxPaint.setAlpha(windyBoxAlpha);
            canvas.drawRoundRect(boxRect, 4, 4, windyBoxPaint);
        }
    }

    //温度的折线,为了折线比较平滑，做了贝塞尔曲线
    private void onDrawLine(Canvas canvas, int i) {
        linePaint.setColor(new Color().YELLOW);
        linePaint.setStrokeWidth(3);
        Point point = listItems.get(i).tempPoint;
        if(i != 0){
            Point pointPre = listItems.get(i-1).tempPoint;
            Path path = new Path();
            path.moveTo(pointPre.x, pointPre.y);
            if(i % 2 == 0)
                path.cubicTo((pointPre.x+point.x)/2, (pointPre.y+point.y)/2-7, (pointPre.x+point.x)/2, (pointPre.y+point.y)/2+7, point.x, point.y);
            else
                path.cubicTo((pointPre.x+point.x)/2, (pointPre.y+point.y)/2+7, (pointPre.x+point.x)/2, (pointPre.y+point.y)/2-7, point.x, point.y);
            canvas.drawPath(path, linePaint);
        }
    }

    //绘制底部时间
    private void onDrawText(Canvas canvas, int i) {
        //此处的计算是为了文字能够居中
        Rect rect = listItems.get(i).windyBoxRect;
        Rect targetRect = new Rect(rect.left, rect.bottom, rect.right, rect.bottom + bottomTextHeight);
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        textPaint.setTextAlign(Paint.Align.CENTER);

        String text = listItems.get(i).time;
        canvas.drawText(text, targetRect.centerX(), baseline, textPaint);
    }


    public void drawLeftTempText(Canvas canvas, int offset){
        //画最左侧的文字
        textPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(maxTemp + "°", DisplayUtil.dip2px(getContext(), 6) + offset, tempBaseTop, textPaint);
        canvas.drawText(minTemp + "°", DisplayUtil.dip2px(getContext(), 6) + offset, tempBaseBottom, textPaint);
    }

    //设置scrollerView的滚动条的位置，通过位置计算当前的时段
    public void setScrollOffset(int offset, int maxScrollOffset){
        this.maxScrollOffset = maxScrollOffset;
        scrollOffset = offset;
        int index = calculateItemIndex(offset);
        currentItemIndex = index;
        invalidate();
    }

    //通过滚动条偏移量计算当前选择的时刻
    private int calculateItemIndex(int offset){
//        Log.d(TAG, "maxScrollOffset = " + maxScrollOffset + "  scrollOffset = " + scrollOffset);
        int x = getScrollBarX();
        int sum = MARGIN_LEFT_ITEM  - ITEM_WIDTH/2;
        for(int i=0; i<ITEM_SIZE; i++){
            sum += ITEM_WIDTH;
            if(x < sum)
                return i;
        }
        return ITEM_SIZE - 1;
    }

    private int getScrollBarX(){
        int x = (ITEM_SIZE - 1) * ITEM_WIDTH * scrollOffset / maxScrollOffset;
        x = x + MARGIN_LEFT_ITEM;
        return x;
    }

    //计算温度提示文字的运动轨迹
    private int getTempBarY(){
        int x = getScrollBarX();
        int sum = MARGIN_LEFT_ITEM ;
        Point startPoint = null, endPoint;
        int i;
        for(i=0; i<ITEM_SIZE; i++){
            sum += ITEM_WIDTH;
            if(x < sum) {
                startPoint = listItems.get(i).tempPoint;
                break;
            }
        }
        if(i+1 >= ITEM_SIZE || startPoint == null)
            return listItems.get(ITEM_SIZE-1).tempPoint.y;
        endPoint = listItems.get(i+1).tempPoint;

        Rect rect = listItems.get(i).windyBoxRect;
        int y = (int)(startPoint.y + (x - rect.left)*1.0/ITEM_WIDTH * (endPoint.y - startPoint.y));
        return y;
    }



    /**
     * 将文件生成位图
     * @param filename
     * @return
     * @throws IOException
     */
    public BitmapDrawable getImageDrawableFromAssets(String filename)
            throws IOException
    {
//        //打开文件
//        File file = new File(path);
//        if(!file.exists())
//        {
//            return null;
//        }

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        final int BUFFER_SIZE = 1024;

        byte[] bt = new byte[BUFFER_SIZE];

        //得到文件的输入流
        InputStream in = context.getAssets().open("weather/" + filename);

        //将文件读出到输出流中
        int readLength = in.read(bt);
        while (readLength != -1) {
            outStream.write(bt, 0, readLength);
            readLength = in.read(bt);
        }

        //转换成byte 后 再格式化成位图
        byte[] data = outStream.toByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);// 生成位图
        BitmapDrawable bd = new BitmapDrawable(bitmap);

        return bd;
    }
}