package org.dzmitry.kapachou.core.scope;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ColorService {

    ObjectFactory<ColorProvider> colorProviderFactory;

    public Color getColor() {
        return getColorProvider().getColor();
    }

    public ColorProvider getColorProvider() {
        return colorProviderFactory.getObject();
    }
}
