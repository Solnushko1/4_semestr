package service;

import data.DataRecord;
import data.DataRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Service
public class DataRecordService {
    @Autowired
    private DataRecordRepository repository;

    @Cacheable("readOnlyData")
    public List<DataRecord> getReadOnlyData() {
        return repository.findByReadOnly(true);
    }

    public List<DataRecord> getAllData() {
        return repository.findAll();
    }

    public DataRecord saveData(DataRecord dataRecord) {
        return repository.save(dataRecord);
    }

    public DataRecord updateData(Long id, DataRecord dataRecord) {
        DataRecord existingRecord = repository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
        existingRecord.setData(dataRecord.getData());
        existingRecord.setReadOnly(dataRecord.isReadOnly());
        return repository.save(existingRecord);
    }

    public void deleteData(Long id) {
        repository.deleteById(id);
    }
}
