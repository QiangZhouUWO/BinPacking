import java.math.BigDecimal;
import java.util.ArrayList;

public class NextFitMethod {

    private ArrayList<Float> items = new ArrayList<Float>();
    private ArrayList<ArrayList<Float>> bins = new ArrayList<ArrayList<Float>>();
    private ArrayList<Float> binsCap;

    public NextFitMethod(ArrayList<Float> inputs) {
        items = inputs;
        binsCap = new ArrayList<Float>();
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
                ArrayList<Float> tempBin = new ArrayList<>();
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