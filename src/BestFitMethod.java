/**
 * Created by qiangzhou on 2017-11-22.
 */

import java.math.BigDecimal;
import java.util.ArrayList;

public class BestFitMethod {

    private ArrayList<Float> items = new ArrayList<Float>();
    private ArrayList<ArrayList<Float>> bins = new ArrayList<ArrayList<Float>>();
    private ArrayList<Float> binsCap;
    private ArrayList<Integer> binOrders;

    public BestFitMethod(ArrayList<Float> inputs) {
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
        int current;
        for (int itemNum = 0; itemNum < items.size(); itemNum++) {
            boolean packed = false;
            outerloop:
            for (int binNum = 0; binNum < binOrders.size(); binNum++) {
                if (items.get(itemNum) <= binsCap.get(binNum)) {
                    bins.get(binOrders.get(binNum)).add(items.get(itemNum));
                    packed = true;
                    float realCap;
                    BigDecimal b = new BigDecimal(binsCap.get(binNum) - items.get(itemNum));
                    realCap = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                    binsCap.set(binNum, realCap);
                    if (binNum >= 1) {
                        if (binsCap.get(binNum) < binsCap.get(binNum - 1)) {
                            current = binNum;
                            mid = (int) Math.floor(current / 2);
                            while (current != mid + 1) {
                                if (binsCap.get(mid) < binsCap.get(binNum)) {
                                    mid = (int) Math.floor((mid + current) / 2);
                                } else if (binsCap.get(mid) > binsCap.get(binNum)) {
                                    current = mid;
                                    mid = (int) Math.floor(current / 2);
                                } else {
                                    float restCap = binsCap.get(binNum);
                                    int order = binOrders.get(binNum);
                                    binsCap.remove(binNum);
                                    binOrders.remove(binNum);
                                    binsCap.add(mid, restCap);
                                    binOrders.add(mid, order);
                                    break outerloop;
                                }
                            }
                            if (binsCap.get(mid) < binsCap.get(binNum)) {
                                float restCap = binsCap.get(binNum);
                                int order = binOrders.get(binNum);
                                binsCap.remove(binNum);
                                binOrders.remove(binNum);
                                binsCap.add(current, restCap);
                                binOrders.add(current, order);
                                break;
                            } else {
                                float restCap = binsCap.get(binNum);
                                int order = binOrders.get(binNum);
                                binsCap.remove(binNum);
                                binOrders.remove(binNum);
                                binsCap.add(mid, restCap);
                                binOrders.add(mid, order);
                                break;
                            }

                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (packed == false) {
                binsCap.add((float) 1);
                ArrayList<Float> tempBin = new ArrayList<Float>();
                bins.add(tempBin);
                binOrders.add(binOrders.size());
                int binNum = binOrders.size() - 1;
                bins.get(binOrders.get(binNum)).add(items.get(itemNum));
                float realCap;
                BigDecimal b = new BigDecimal(binsCap.get(binOrders.get(binNum)) - items.get(itemNum));
                realCap = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                binsCap.set(binNum, realCap);
                if (binNum >= 1) {
                    if (binsCap.get(binNum) < binsCap.get(binNum - 1)) {
                        current = binNum;
                        mid = (int) Math.floor(current / 2);
                        while (current != mid + 1) {
                            if (binsCap.get(mid) < binsCap.get(binNum)) {
                                mid = (int) Math.floor((mid + current) / 2);
                            } else if (binsCap.get(mid) > binsCap.get(binNum)) {
                                current = mid;
                                mid = (int) Math.floor(current / 2);
                            } else {
                                float restCap = binsCap.get(binNum);
                                int order = binOrders.get(binNum);
                                binsCap.remove(binNum);
                                binOrders.remove(binNum);
                                binsCap.add(mid, restCap);
                                binOrders.add(mid, order);
                                continue;
                            }
                        }
                        if (binsCap.get(mid) < binsCap.get(binNum)) {
                            float restCap = binsCap.get(binNum);
                            int order = binOrders.get(binNum);
                            binsCap.remove(binNum);
                            binOrders.remove(binNum);
                            binsCap.add(current, restCap);
                            binOrders.add(current, order);
                            continue;
                        } else {
                            float restCap = binsCap.get(binNum);
                            int order = binOrders.get(binNum);
                            binsCap.remove(binNum);
                            binOrders.remove(binNum);
                            binsCap.add(mid, restCap);
                            binOrders.add(mid, order);
                            continue;
                        }
                    }
                }
            }
        }
        return bins;
    }
}