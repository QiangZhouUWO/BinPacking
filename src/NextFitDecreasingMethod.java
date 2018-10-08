/**
 * Created by qiangzhou on 2017-11-22.
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class NextFitDecreasingMethod {

    private ArrayList<Float> items = new ArrayList<Float>();
    private ArrayList<ArrayList<Float>> bins = new ArrayList<ArrayList<Float>>();
    private ArrayList<Float> binsCap;

    public NextFitDecreasingMethod(ArrayList<Float> inputs) {
        items = inputs;
        binsCap = new ArrayList<Float>();
        Collections.sort(items, Collections.reverseOrder());
    }

    public ArrayList<ArrayList<Float>> fit() {
        binsCap.add((float) 1);
        ArrayList<Float> bin = new ArrayList<Float>();
        bins.add(bin);
        int binNum = 0;
        for (int itemNum = 0; itemNum < items.size(); itemNum++) {
            if (items.get(itemNum) <= binsCap.get(binNum)) {
                float f = items.get(itemNum);
                bins.get(binNum).add(f);
                float realCap;
                BigDecimal b = new BigDecimal(binsCap.get(binNum) - items.get(itemNum));
                realCap = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                binsCap.set(binNum, realCap);
            } else {
                binsCap.add((float) 1);
                ArrayList<Float> tempBin = new ArrayList<Float>();
                bins.add(tempBin);
                binNum++;
                float f = items.get(itemNum);
                bins.get(binNum).add(f);
                float realCap;
                BigDecimal b = new BigDecimal(binsCap.get(binNum) - items.get(itemNum));
                realCap = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                binsCap.set(binNum, realCap);
            }
        }
        return bins;
    }
}
