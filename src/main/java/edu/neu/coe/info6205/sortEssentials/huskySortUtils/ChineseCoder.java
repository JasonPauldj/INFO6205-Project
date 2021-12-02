package edu.neu.coe.info6205.sortEssentials.huskySortUtils;

import java.text.Collator;

public class ChineseCoder implements HuskyCoder<String>{

    private Collator collator;
    private com.ibm.icu.text.Collator ibmCollator;
    boolean inBuiltCollator;

    public ChineseCoder(Collator cl) {
        collator=cl;
        inBuiltCollator=true;
    }

    public ChineseCoder(com.ibm.icu.text.Collator cl) {
        ibmCollator=cl;
        inBuiltCollator=false;
    }

    @Override
    public long huskyEncode(String x) {
        String strRep= inBuiltCollator ?  new String(collator.getCollationKey(x).toByteArray()) : new String(ibmCollator.getCollationKey(x).toByteArray());
        return HuskyCoderFactory.unicodeCoder.huskyEncode(strRep);
    }
}
