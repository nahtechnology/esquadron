package tecolotl.web.herramienta;

import tecolotl.alumno.cache.RelacionarImagenCache;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

//@WebServlet(name = "ServletImagenRelacionar", urlPatterns = "imagen-relacionar")
public class ImagenRelacionarServlet extends HttpServlet {

    @Inject
    private RelacionarImagenCache relacionarImagenCache;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        byte[] buffer = new byte[1024];
        int byteLeidos;
        httpServletResponse.setContentType("image/jpeg");
        try(
            OutputStream outputStream = httpServletResponse.getOutputStream();
            InputStream inputStream = new ByteArrayInputStream(relacionarImagenCache.busca(
                    httpServletRequest.getParameter("codigo")
            ))){
         while ((byteLeidos = inputStream.read(buffer)) > -1){
             outputStream.write(buffer, 0, byteLeidos);
         }
        }
    }
}
