package org.dzmitry.kapachou.core.scope;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Random;

@Data
@Scope("prototype")
@Component
@AllArgsConstructor
public class ColorProvider {

    private final Color color = getDefinedColor();

    private static Color getDefinedColor() {
        return Color.values()[new Random().nextInt(Color.values().length)];
    }
}
