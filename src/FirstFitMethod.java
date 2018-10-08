/**
 * Created by qiangzhou on 2017-11-22.
 */

import java.math.BigDecimal;
import java.util.ArrayList;

public class FirstFitMethod {

    private ArrayList<Float> items = new ArrayList<Float>();
    private ArrayList<ArrayList<Float>> bins = new ArrayList<ArrayList<Float>>();
    private ArrayList<Float> binsCap;

    public FirstFitMethod(ArrayList<Float> inputs) {
        items = inputs;
        binsCap = new ArrayList<Float>();
    }

    public ArrayList<ArrayList<Float>> fit() {
        binsCap.add((float) 1);
        ArrayList<Float> bin = new ArrayList<Float>();
        bins.add(bin);
        boolean packed = false;
        for (int itemNum = 0; itemNum < items.size(); itemNum++) {
            for (int binNum = 0; binNum < binsCap.size(); binNum++) {
                if (items.get(itemNum) <= binsCap.get(binNum)) {
                    float f = items.get(itemNum);
                    bins.get(binNum).add(f);
                    float realCap;
                    BigDecimal b = new BigDecimal(binsCap.get(binNum) - items.get(itemNum));
                    realCap = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                    binsCap.set(binNum, realCap);
                    packed = true;
                    break;
                }
            }
            if (packed == false) {
                binsCap.add((float) 1);
                ArrayList<Float> tempBin = new ArrayList<Float>();
                bins.add(tempBin);
                bins.get(binsCap.size() - 1).add(items.get(itemNum));
                float realCap;
                BigDecimal b = new BigDecimal(binsCap.get(binsCap.size() - 1) - items.get(itemNum));
                realCap = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                binsCap.set(binsCap.size() - 1, realCap);
            }
            packed = false;
        }
        return bins;
    }
}