public class ResourceCard extends Card{
    public ResourceCard() {
        super();
    }
    public static Card parseCard(String line) {
        Card card = new ResourceCard();
        card.loadCard(line);
        return card;
    }

    @Override
    public void loadCard(String lines) {
    	String[] cardInfo = lines.split(",(?! )");
//        this.name = cardInfo[1];
//        this.description = cardInfo[2];
//        this.vp = Integer.parseInt(cardInfo[3]);
//        this.sciencePoints = Integer.parseInt(cardInfo[4]);
//        this.economyPoints = Integer.parseInt(cardInfo[5]);
        setName(cardInfo[1]);
        setDescription(cardInfo[2]);
        setVp(Integer.parseInt(cardInfo[3]));
        setSciencePoints(Integer.parseInt(cardInfo[4]));
        setEconomyPoints(Integer.parseInt(cardInfo[5]));
    }
}
