package data;

import data.DataRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataRecordRepository extends JpaRepository<DataRecord, Long> {
    List<DataRecord> findByReadOnly(boolean readOnly);
}
