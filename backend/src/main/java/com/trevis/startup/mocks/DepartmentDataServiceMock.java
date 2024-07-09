package com.trevis.startup.mocks;

import com.trevis.startup.entities.DepartmentData;
import com.trevis.startup.exceptions.NotFoundException;
import com.trevis.startup.interfaces.DepartmentDataService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentDataServiceMock implements DepartmentDataService {

    private static final List<DepartmentData> departmentDataList = new ArrayList<DepartmentData>();

    public DepartmentDataServiceMock() {
        var BDO = new DepartmentData("BDO");
        BDO.setId(1L);
        departmentDataList.add(BDO);

        var ICO = new DepartmentData("ICO");
        ICO.setId(2L);
        departmentDataList.add(ICO);

        var ETS = new DepartmentData("ETS");
        ETS.setId(3L);
        departmentDataList.add(ETS);
    }


    @Override
    public List<DepartmentData> getAll() {
        return departmentDataList;
    }

    @Override
    public DepartmentData getById(Long id) {
        var query = departmentDataList
                .stream()
                .filter(department -> department.getId().equals(id))
                .findFirst();

        if(query.isEmpty()) throw new NotFoundException();

        return query.get();
    }
}
