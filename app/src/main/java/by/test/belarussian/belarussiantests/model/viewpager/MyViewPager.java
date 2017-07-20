package by.test.belarussian.belarussiantests.model.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {
    private float initialXValue;
    private Direction direction;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.direction = Direction.ALL;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (this.IsSwipeAllowed(ev)) {
            return super.onTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.IsSwipeAllowed(ev)) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    private boolean IsSwipeAllowed(MotionEvent event) {
        if (this.direction == Direction.ALL) {
            return true;
        }

        if (direction == Direction.NONE) {
            return false;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            initialXValue = event.getX();
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            try {
                float diffX = event.getX() - initialXValue;
                if (diffX > 0 && direction == Direction.RIGHT) {
                    return false;
                } else if (diffX < 0 && direction == Direction.LEFT) {
                    return false;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return true;
    }

    public void setAllowedSwipeDirection(Direction direction) {
        this.direction = direction;
    }

    public enum Direction {
        ALL,
        LEFT,
        RIGHT,
        NONE
    }
}
