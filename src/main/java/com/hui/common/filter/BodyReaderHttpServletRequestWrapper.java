package com.hui.common.filter;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014年7月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper
{
    
    private ByteArrayOutputStream cachedBytes;
    
    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request)
    {
        super(request);
    }
    
    @Override
    public ServletInputStream getInputStream()
        throws IOException
    {
        if (null == cachedBytes)
        {
            cacheInputStream();
        }
        
        return new CachedServletInputStream();
    }
    
    private void cacheInputStream()
        throws IOException
    {
        /* Cache the inputstream in order to read it multiple times. For
        * convenience, I use apache.commons IOUtils
        */
        cachedBytes = new ByteArrayOutputStream();
        IOUtils.copy(super.getInputStream(), cachedBytes);
    }
    
    /* An inputstream which reads the cached request body */
    public class CachedServletInputStream extends ServletInputStream
    {
        private ByteArrayInputStream input;
        
        public CachedServletInputStream()
        {
            /* create a new input stream from the cached request body */
            input = new ByteArrayInputStream(cachedBytes.toByteArray());
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }

        @Override
        public int read()
            throws IOException
        {
            return input.read();
        }
    }
    
}