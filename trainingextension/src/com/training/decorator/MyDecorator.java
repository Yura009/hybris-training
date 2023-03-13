package com.training.decorator;

import de.hybris.platform.util.CSVCellDecorator;

import java.util.Locale;
import java.util.Map;

public class MyDecorator implements CSVCellDecorator {
    @Override
    public String decorate(int position, Map<Integer, String> map) {
        final String csvCell = map.get(position);
        if (csvCell.isEmpty()) {
            return csvCell;
        }
        return csvCell.replaceAll("\\s+", "").toLowerCase(Locale.getDefault());
    }
}
