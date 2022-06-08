package constants;

public enum InventoryItems {
    TEDDY_BEAR("Teddy Bear"),
    HANDMADE_DOLL("Handmade Doll"),
    STUFFED_FROG("Stuffed Frog"),
    FLUFFY_BUNNY("Fluffy Bunny"),
    SMILEY_BEAR("Smiley Bear"),
    FUNNY_COW("Funny Cow"),
    VALENTINE_BEAR("Valentine Bear"),
    SMILEY_FACE("Smiley Face");

    private String item;

    InventoryItems(String item) {
        this.item = item;
    }

    public String getItemName() {
        return this.item;
    }

}
