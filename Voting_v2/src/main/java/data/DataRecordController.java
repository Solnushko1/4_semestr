package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.DataRecordService;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataRecordController {
    @Autowired
    private DataRecordService service;

    @GetMapping
    public List<DataRecord> getAllData() {
        return service.getAllData();
    }

    @GetMapping("/readonly")
    public List<DataRecord> getReadOnlyData() {
        return service.getReadOnlyData();
    }

    @PostMapping
    public DataRecord createData(@RequestBody DataRecord dataRecord) {
        return service.saveData(dataRecord);
    }

    @PutMapping("/{id}")
    public DataRecord updateData(@PathVariable Long id, @RequestBody DataRecord dataRecord) {
        return service.updateData(id, dataRecord);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable Long id) {
        service.deleteData(id);
    }
}
