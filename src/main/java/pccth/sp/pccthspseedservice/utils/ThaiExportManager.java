package pccth.sp.pccthspseedservice.utils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.ByteArrayOutputStream;

public class ThaiExportManager {

    public static byte[] exportPdf(JasperPrint jasperPrint) throws JRException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ThaiExporter thaiExporter = new ThaiExporter();
        thaiExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        thaiExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
        thaiExporter.exportReport();

        return baos.toByteArray();
    }
}
