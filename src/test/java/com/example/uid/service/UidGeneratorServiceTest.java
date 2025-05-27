package com.example.uid.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.wukong.uid.config.UidProperties;
import com.wukong.uid.mapper.TinyIdInfoMapper;
import com.wukong.uid.model.TinyIdInfo;
import com.wukong.uid.service.UidGeneratorService;

import static org.mockito.ArgumentMatchers.*;

public class UidGeneratorServiceTest {
    private TinyIdInfoMapper mapper;
    private UidProperties properties;
    private UidGeneratorService service;

    @BeforeEach
    public void setUp() {
        mapper = Mockito.mock(TinyIdInfoMapper.class);
        properties = new UidProperties();
        properties.setToken("order_id");
        TinyIdInfo info = new TinyIdInfo();
        info.setBizType("order_id");
        info.setMaxId(1000L);
        info.setStep(1000);
        info.setVersion(1L);
        Mockito.when(mapper.selectByBizType(anyString())).thenReturn(info);
        Mockito.when(mapper.updateMaxId(anyString(), anyLong(), anyLong(), anyLong())).thenReturn(1);
        service = new UidGeneratorService(mapper, properties);
    }

    @Test
    public void testGetId() {
      
        for (int i = 0; i < 100; i++) {
            System.out.println(service.getId("order_id"));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}