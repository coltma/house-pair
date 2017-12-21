package demo.model;

public interface HouseDataRepositoryCustom {
    public HouseData findSameHouse(HouseData current);
    public void update(HouseData old, HouseData current);
}
