/**
 * Created by qiangzhou on 2017-11-22.
 */

import java.math.BigDecimal;
import java.util.ArrayList;

public class WorstFitMethod {

    private ArrayList<Float> items = new ArrayList<Float>();
    private ArrayList<ArrayList<Float>> bins = new ArrayList<ArrayList<Float>>();
    private ArrayList<Float> binsCap;
    private ArrayList<Integer> binOrders;

    public WorstFitMethod(ArrayList<Float> inputs) {
        items = inputs;
        binsCap = new ArrayList<Float>();
        binOrders = new ArrayList<Integer>();
    }

    public ArrayList<ArrayList<Float>> fit() {
        binsCap.add((float) 1);
        ArrayList<Float> bin = new ArrayList<Float>();
        bins.add(bin);
        binOrders.add(0);
        int mid;
        int end;
        for (int itemNum = 0; itemNum < items.size(); itemNum++) {
            if (items.get(itemNum) <= binsCap.get(0)) {
                bins.get(binOrders.get(0)).add(items.get(itemNum));
                float realCap;
                BigDecimal b = new BigDecimal(binsCap.get(0) - items.get(itemNum));
                realCap = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                binsCap.set(0, realCap);
            } else {
                binsCap.add(0, (float) 1);
                ArrayList<Float> tempBin = new ArrayList<Float>();
                bins.add(tempBin);
                binOrders.add(0, binOrders.size());
                bins.get(binOrders.get(0)).add(items.get(itemNum));
                float realCap;
                BigDecimal b = new BigDecimal(binsCap.get(0) - items.get(itemNum));
                realCap = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                binsCap.set(0, realCap);
            }
            if (binOrders.size() > 1) {
                if (binsCap.get(0) < binsCap.get(1)) {
                    end = binsCap.size() - 1;
                    mid = (int) Math.floor(binOrders.size() / 2);
                    if (binsCap.get(0) <= binsCap.get(end)) {
                        float restCap = binsCap.get(0);
                        int order = binOrders.get(0);
                        binsCap.add(restCap);
                        binOrders.add(order);
                        binsCap.remove(0);
                        binOrders.remove(0);
                        continue;
                    }
                    while (end != mid + 1) {
                        if (binsCap.get(mid) > binsCap.get(0)) {
                            mid = (int) Math.floor((mid + end) / 2);
                        } else if (binsCap.get(mid) < binsCap.get(0)) {
                            end = mid;
                            mid = (int) Math.floor(end / 2);
                        } else {
                            float restCap = binsCap.get(0);
                            int order = binOrders.get(0);
                            binsCap.add(mid, restCap);
                            binOrders.add(mid, order);
                            binsCap.remove(0);
                            binOrders.remove(0);
                            continue;
                        }
                    }
                    if (binsCap.get(0) > binsCap.get(mid)) {
                        float restCap = binsCap.get(0);
                        int order = binOrders.get(0);
                        binsCap.add(mid, restCap);
                        binOrders.add(mid, order);
                        binsCap.remove(0);
                        binOrders.remove(0);
                    } else {
                        float restCap = binsCap.get(0);
                        int order = binOrders.get(0);
                        binsCap.add(mid + 1, restCap);
                        binOrders.add(mid + 1, order);
                        binsCap.remove(0);
                        binOrders.remove(0);
                    }
                }
            }
        }
        return bins;
    }
}