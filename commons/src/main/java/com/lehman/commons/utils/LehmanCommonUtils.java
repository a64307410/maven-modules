package com.lehman.commons.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;

/**
 * 2015年5月8日 16:13:09 雷建军
 * 自己用的一些Util方法
 */


public class LehmanCommonUtils {



    /**
     * 根据分页对象,创建JSON分页视图数据,方便前端js 分页
     */
    public static JSONObject createPageView( Page< ? > page ) {
        //region 视图数据填充
        JSONObject view = new JSONObject();
        view.put( "lastPage", page.isLastPage() ); // 是否是最后一页
        view.put( "hasPreviousPage", page.hasPreviousPage() ); // 是否有上一页
        view.put( "hasNextPage", page.hasNextPage() ); // 是否有下一页
        view.put( "totalPages", page.getTotalPages() ); // 总页数
        view.put( "totalElements", page.getTotalElements() ); // 记录数量
        view.put( "number", page.getNumber() ); // 页码
        view.put( "size", page.getSize() ); // 页大小
        //endregion
        return view;
    }





}
