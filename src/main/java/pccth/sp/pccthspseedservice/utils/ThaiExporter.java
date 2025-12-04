package pccth.sp.pccthspseedservice.utils;

import com.lowagie.text.Chunk;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import java.text.AttributedCharacterIterator;
import java.util.Locale;
import java.util.Map;

public class ThaiExporter extends JRPdfExporter {

    @Override
    protected Chunk getChunk(Map<AttributedCharacterIterator.Attribute, Object> attributes, String text, Locale locale) {
        return new ThaiChunk(super.getChunk(attributes, text, locale));
    }
}
