package DAOLayer;

import EntityLayer.Magazine;

import java.util.List;

public interface DAOMagazine {

    void addNewMagazine(String name_m);

    void updateMagazine(Magazine magazine);

    void deleteMagazine(int id_magazine);

    Magazine getMagazine(int id_magazine);

    String getNameMagazineByIdMagazine(int id_magazine);

    List<Magazine> getAllNameMagazineUserTrue();

    List<Magazine> getAllNameMagazineByAllUser();

}
