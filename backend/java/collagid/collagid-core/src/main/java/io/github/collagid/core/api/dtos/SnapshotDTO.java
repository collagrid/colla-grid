package io.github.collagid.core.api.dtos;

import io.github.collagid.core.api.event.CollaGridEventPublisher;
import io.github.collagid.core.api.field.FieldDTO;
import io.github.collagid.core.api.record.event.RecordCreatedEvent;
import io.github.collagid.core.api.record.event.RecordQueryEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnapshotDTO{
    private String dstId;
    private List<ViewDTO> views;
    private Map<String, FieldDTO> fieldMap;
    private Map<String, RecordDTO> recordMap;
    private int v;

    public SnapshotDTO() {
        this.views = new ArrayList<>();
        this.fieldMap = new HashMap<>();
        this.recordMap = new HashMap<>();
    }

    public Integer recordCount(){
        return this.recordMap.size();
    }

    public void addView(ViewDTO viewDTO) {
        this.views.add(viewDTO);
        for (ChunkDTO chunk : viewDTO.getChunks()) {
            for (RecordDTO record : chunk.getRecords()) {
                recordMap.put(record.getId(), record);
            }
        }
    }

    private void addRecords(List<RecordDTO> recordDTOS) {
        for (RecordDTO recordDTO : recordDTOS) {
            this.addRecord(recordDTO);
        }
    }

    public String getDstId() {
        return dstId;
    }

    public void addField(FieldDTO fieldDTO) {
        this.fieldMap.put(fieldDTO.getId(), fieldDTO);
    }

    public void setDstId(String dstId) {
        this.dstId = dstId;
    }

    public RecordDTO addRecord(RecordDTO recordDTO){
        CollaGridEventPublisher.publishEvent(
            new RecordCreatedEvent(this, recordDTO)
        );
        this.recordMap.put(recordDTO.getId(), recordDTO);
        return recordDTO;
    }

    public Map<String, RecordDTO> getRecordMap() {
        return recordMap;
    }

    public Map<String, FieldDTO> getFieldMap() {
        return fieldMap;
    }

    public List<ViewDTO> getViews() {
        return views;
    }
}