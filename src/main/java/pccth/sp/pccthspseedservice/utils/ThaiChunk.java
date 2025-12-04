package pccth.sp.pccthspseedservice.utils;

import com.lowagie.text.Chunk;
import pccth.sp.pccthspseedservice.utils.lexto.ThaiDisplayUtils;

public class ThaiChunk extends Chunk {

    public ThaiChunk(Chunk chunk) {
        super(chunk);
        manageContent();
    }

    public ThaiChunk(String content) {
        super(content);
        manageContent();
    }

    private void manageContent() {
        ThaiDisplayUtils.toDisplayString(content);
    }
}
