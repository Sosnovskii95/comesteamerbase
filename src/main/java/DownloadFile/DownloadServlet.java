package DownloadFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.server.ExportException;

@WebServlet(name = "DownloadServlet", urlPatterns = "/adminview/download")
public class DownloadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException {
        //Принимаем из запроса. Путь к файлу. Связываем его с переменной
        File file = new File(request.getAttribute("pathName").toString());
        ServletOutputStream outputStream = null;
        BufferedInputStream inputStream = null;
        try {
            outputStream = response.getOutputStream();//Получаем поток для отправки из сервлета
            response.setContentType("application/vnd.ms-excel");//Создаем тип того, что будем отправлять
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName() + "");//Создаем заголовок отправки
            response.setContentLength((int) file.length());
            FileInputStream fileInputStream = new FileInputStream(file);//Переносим содержимое файла в потом
            inputStream = new BufferedInputStream(fileInputStream);
            int readBytes;
            while ((readBytes = inputStream.read()) != -1)//Читаем файл
                outputStream.write(readBytes);//Записываем в поток из сервлета
        }catch (ExportException e){
            e.printStackTrace();
        }finally {
            //Закрываем потоки
            if (outputStream != null)
            {
                outputStream.flush();
                outputStream.close();
            }
            if (inputStream != null)
            {
                inputStream.close();
            }
        }
        //Перенаправление браузера на страницу отчетов
        //response.sendRedirect("adminview/report/report.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);//Так как из сервлета можно вызвать только с помощью ГЕТ запроса. Вызываем doPost
    }

}
