package umu.tds.utilidades;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import umu.tds.dominio.Cancion;
import umu.tds.dominio.PlayList;
import umu.tds.dominio.Usuario;

public enum CreadorPDF {
	INSTANCE; 
	
    private static final String DIRECTORIO_PDF = "./pdf";

    public void generarPDF(Usuario usuario) throws FileNotFoundException, DocumentException {
        List<PlayList> listas = usuario.getPlayLists();
        Document pdf = crearPDF(usuario);
        pdf.open();

        pdf.add(new Paragraph("Listas de canciones de " + usuario.getNombre() + ":", 
                new Font(FontFamily.HELVETICA, 20, Font.ITALIC)));
        for (PlayList lista : listas) {
            pdf.add(new Paragraph(lista.getNombre(), 
                    new Font(FontFamily.HELVETICA, 18, Font.BOLD)));
            pdf.add(Chunk.SPACETABBING);
            pdf.add(tablaFromPlayList(lista));
            pdf.add(Chunk.NEWLINE);
        }
        pdf.close();
    }

    private static PdfPTable tablaFromPlayList(PlayList lista) {
        PdfPTable tabla = new PdfPTable(3);
        tabla.addCell(new PdfPCell(new Paragraph("Título")));
        tabla.addCell(new PdfPCell(new Paragraph("Intérprete")));
        tabla.addCell(new PdfPCell(new Paragraph("Estilo")));
        tabla.setHeaderRows(1);
        for (Cancion cancion : lista.getCanciones()) {
            tabla.addCell(new PdfPCell(new Paragraph(cancion.getTitulo())));
            tabla.addCell(new PdfPCell(new Paragraph(cancion.getInterprete())));
            tabla.addCell(new PdfPCell(new Paragraph(cancion.getEstilo())));
        }
        return tabla;
    }


    private static Document crearPDF(Usuario usuario) throws FileNotFoundException, DocumentException {
        FileOutputStream outputFile = new FileOutputStream(DIRECTORIO_PDF + "/" + usuario.getUsername() + ".pdf");
        Document pdf = new Document();
        PdfWriter.getInstance(pdf, outputFile);
        return pdf;
    }
}
