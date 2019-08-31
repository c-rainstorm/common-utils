package me.rainstorm.common.util.export.formatter.impl;

import me.rainstorm.common.util.export.entity.ColMata;
import me.rainstorm.common.util.export.formatter.AbstractFieldFormatter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author baochen1.zhang
 * @date 2019.08.31
 */
public class CharFormatter extends AbstractFieldFormatter<Character> {
    @Override
    public Set<Class> getValueTypes() {
        Set<Class> kls = new HashSet<>();
        kls.add(Character.class);
        kls.add(Character.TYPE);
        return kls;
    }

    @Override
    public String apply(ColMata colMata, Character character) {
        if (Objects.isNull(character) || Character.isISOControl(character)) {
            return "";
        }
        
        return character.toString();
    }
}
