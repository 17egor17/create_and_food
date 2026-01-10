package net.egorplaytv.caf.util;

public enum LocalizationTooltips implements LocalizationEnum {
    ;

    private final String englishText;

    LocalizationTooltips(String englishText) {
        this.englishText = englishText;
    }


    @Override
    public String getTranslationKey() {
        return "tooltip.caf." + name();
    }

    @Override
    public String getEnglishText() {
        return englishText;
    }
}
