package xyz.sahildave.arclayout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;


public class ArcLayoutSettings {
    private static final String TAG = "ArcLayoutSettings";

    public final static int CROP_CONVEX = 1;
    public final static int CROP_CONCAVE = 2;
    public final static int DIRECTION_BOTTOM = 1;
    public final static int DIRECTION_TOP = 2;

    private static final int DEFAULT_ARC_HEIGHT = 32;

    private boolean cropConvex = true;
    private boolean directionBottom = true;
    private float arcHeight;
    private float elevation;

    private static float dpToPx(Context context, int dp) {
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    ArcLayoutSettings(Context context, AttributeSet attrs) {
        TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.ArcHeader, 0, 0);
        arcHeight = styledAttributes.getDimension(R.styleable.ArcHeader_arc_height, dpToPx(context, DEFAULT_ARC_HEIGHT));

        final int cropDirection = styledAttributes.getInt(R.styleable.ArcHeader_arc_cropCurve, CROP_CONVEX);
        cropConvex = (cropDirection & CROP_CONVEX) == CROP_CONVEX;

        final int arcPosition = styledAttributes.getInt(R.styleable.ArcHeader_arc_position, DIRECTION_BOTTOM);
        directionBottom = (arcPosition & DIRECTION_BOTTOM) == DIRECTION_BOTTOM;

        styledAttributes.recycle();
    }

    public float getElevation() {
        return elevation;
    }

    public void setElevation(float elevation) {
        this.elevation = elevation;
    }

    public boolean isCropConvex() {
        return cropConvex;
    }

    public void setIsCropConvex(final boolean cropConvex) {
        this.cropConvex = cropConvex;
    }

    public void setIsDirectionBottom(final boolean directionBottom) {
        this.directionBottom = directionBottom;
    }

    public boolean isDirectionBottom() {
        return directionBottom;
    }

    public float getArcHeight() {
        return arcHeight;
    }
}
