package com.lehman.commons.utils;

import com.frame.commons.vo.ProgressEntity;
import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpSession;

public class MyProgressListener implements ProgressListener {
    private HttpSession session;

    public MyProgressListener() {
    }

    public MyProgressListener( HttpSession _session ) {
        session = _session;
        ProgressEntity ps = new ProgressEntity();
        session.setAttribute( "upload_ps", ps );
    }

    public void update( long pBytesRead, long pContentLength, int pItems ) {
        ProgressEntity ps = ( ProgressEntity ) session.getAttribute( "upload_ps" );
        long loadSpeed = ( ps.getpContentLength() - ps.getpBytesRead() ) - ( pContentLength - pBytesRead );
        ps.setLoadSpeed( ps.getpContentLength() - ps.getpBytesRead() );
        ps.setpBytesRead( pBytesRead );
        ps.setpContentLength( pContentLength );
        ps.setpItems( pItems );
        ps.setRemainingTime( loadSpeed );
        //更新{"pBytesRead":90623781,"pContentLength":90623781,"pItems":7}375107  371709
        session.setAttribute( "upload_ps", ps );
    }

}
