package com.lehman.commons.utils;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 上传进度条
 * 重写CommonsMultipartResolver以监听文件上传进度 ,用来做上传进度条
 */
/*  applicationContext.xml 加入:

    <!--  这里申明的id必须为multipartResolver,文件上传解析器,文件上传支持  -->
	<bean id="multipartResolver" class="com.frame.commons.utils.PJCommonsMultipartResolver">
		<property name="defaultEncoding" value="UTF-8"/>
		<!-- 内存缓存1M -->
		<property name="maxInMemorySize" value="1000000"/>
		<!-- 上传文件,最大2GB -->
		<property name="maxUploadSize" value="2000000000"/>
	</bean>
*/
public class PJCommonsMultipartResolver extends CommonsMultipartResolver {

    private HttpServletRequest request;

    protected FileUpload newFileUpload(FileItemFactory fileItemFactory) {
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
        upload.setSizeMax(-1);
        if (request != null) {
            HttpSession session = request.getSession();
            PJProgressListener uploadProgressListener = new PJProgressListener(session);
            upload.setProgressListener(uploadProgressListener);
        }
        return upload;
    }

    public MultipartHttpServletRequest resolveMultipart(
            HttpServletRequest request) throws MultipartException {
        this.request = request;// 获取到request,要用到session  
        return super.resolveMultipart(request);
    }


    @SuppressWarnings("unchecked")
    @Override
    public MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {

        HttpSession session = request.getSession();

        String encoding = "utf-8";
        FileUpload fileUpload = prepareFileUpload(encoding);

        PJProgressListener uploadProgressListener = new PJProgressListener(session);
        fileUpload.setProgressListener(uploadProgressListener);
        try {
            List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
            return parseFileItems(fileItems, encoding);
        } catch (FileUploadBase.SizeLimitExceededException ex) {
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
        } catch (FileUploadException ex) {
            throw new MultipartException("Could not parse multipart servlet request", ex);
        }
    }


    /**
     * 用于监听上传文件的进度,
     */
    private static class PJProgressListener implements ProgressListener {
        private HttpSession session;

        public PJProgressListener() {
        }

        public PJProgressListener(HttpSession session) {
            this.session = session;
            ProgressVO ps = new ProgressVO();
            this.session.setAttribute("upload_ps", ps);
        }

        public void update(long pBytesRead, long pContentLength, int pItems) {
            ProgressVO ps = (ProgressVO) session.getAttribute("upload_ps");
            long loadSpeed = (ps.getpContentLength() - ps.getpBytesRead()) - (pContentLength - pBytesRead);
            ps.setLoadSpeed(ps.getpContentLength() - ps.getpBytesRead());
            ps.setpBytesRead(pBytesRead);
            ps.setpContentLength(pContentLength);
            ps.setpItems(pItems);
            ps.setRemainingTime(loadSpeed);
            //更新{"pBytesRead":90623781,"pContentLength":90623781,"pItems":7}375107  371709
            session.setAttribute("upload_ps", ps);
        }

    }


    /**
     * 文件上传进度信息
     */
    private static class ProgressVO {
        private long pBytesRead = 0L;
        private long pContentLength = 0L;
        private long loadSpeed = 0L;
        private long remainingTime = 0L;
        private int pItems;

        public long getpBytesRead() {
            return pBytesRead;
        }

        public void setpBytesRead(long pBytesRead) {
            this.pBytesRead = pBytesRead;
        }

        public long getpContentLength() {
            return pContentLength;
        }

        public void setpContentLength(long pContentLength) {
            this.pContentLength = pContentLength;
        }

        public int getpItems() {
            return pItems;
        }

        public void setpItems(int pItems) {
            this.pItems = pItems;
        }

        public long getLoadSpeed() {
            return loadSpeed;
        }

        public void setLoadSpeed(long loadSpeed) {
            //String rtnStr= "0KB";
            //loadSpeed = loadSpeed/1024;
            //rtnStr = loadSpeed + "KB";
            //if(loadSpeed/1024 > 1){
            //	rtnStr = loadSpeed + "MB";
            //}
            this.loadSpeed = loadSpeed;
        }


        public long getRemainingTime() {
            return remainingTime;
        }

        public void setRemainingTime(long loadSpeed) {
        /*
        if(pContentLength != 0 && loadSpeed != 0){
			long remainingSize = pContentLength - pBytesRead;

			remainingSize = remainingSize / loadSpeed;
			long hour = 0, minute = 0, second = 0;
			hour=remainingSize/60/60;
		    minute=remainingSize/60%60;
		    second=remainingSize%60;
			this.remainingTime = loadSpeed;//(hour < 10? "0"+hour:hour)+":"+(minute<10? "0"+minute:minute)+":"+(second<10? "0"+second:second);
		}*/
            this.remainingTime = loadSpeed;
        }

        @Override
        public String toString() {
            return "ProgressVO [pBytesRead=" + pBytesRead + ", pContentLength="
                    + pContentLength + ", loadSpeed=" + loadSpeed
                    + ", remainingTime=" + remainingTime + ", pItems=" + pItems
                    + "]";
        }
    }
}  