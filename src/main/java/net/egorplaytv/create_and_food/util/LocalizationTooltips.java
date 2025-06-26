package net.egorplaytv.create_and_food.util;

public enum LocalizationTooltips implements LocalizationEnum {
    ;

    private final String englishText;

    LocalizationTooltips(String englishText) {
        this.englishText = englishText;
    }


    @Override
    public String getTranslationKey() {
        return "tooltip.create_and_food." + name();
    }

    @Override
    public String getEnglishText() {
        return englishText;
    }
}
