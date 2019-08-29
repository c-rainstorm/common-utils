package me.rainstorm.common.util.export.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author baochen1.zhang
 * @date 2019.08.28
 */
@Slf4j
public class ColMataTest {
    @Test
    public void headBuild() {
        ColMata colMata = ColMata.builder().property("xxxx").head("xxxx").build();
        log.info(colMata.toString());
    }

    @Test
    public void testOrder() {
        int size = 10;
        List<ColMata> colMatadata = new ArrayList<>(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            colMatadata.add(ColMata.builder().property(UUID.randomUUID().toString()).head(UUID.randomUUID().toString()).order(random.nextInt(100)).build());
        }

        colMatadata.stream().sorted().forEach(x -> log.info(x.toString()));
    }




}
