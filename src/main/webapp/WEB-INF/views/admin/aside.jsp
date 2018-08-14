<%@ page contentType="text/html;charset=UTF-8" %>
<aside class="main-sidebar">
    <section class="sidebar">
        <!-- 用户信息 -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${ch.userAvatar(req)}" class="img-circle" alt="头像">
            </div>
            <div class="pull-left info">
                <p>${ch.userNickname(req)}</p>
                <span><i class="fa fa-circle text-success"></i><small> ${ch.userRolename(req)}</small></span>
            </div>
        </div>
        <!-- 查找控件 -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="全局查找...">
                <span class="input-group-btn">
                    <button type="submit" name="search" id="search-btn" class="btn btn-flat">
                        <i class="fa fa-search"></i>
                    </button>
                </span>
            </div>
        </form>
        <!-- 功能菜单 -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header"><b>功能菜单</b></li>
            <!-- 文章 -->
            <li class="treeview ${param.pageCatalog=='article'?'active':''}">
                <a href="#">
                    <i class="fa fa-graduation-cap"></i><span> 文章</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.pageMenu=='article_list'?'active':''}">
                        <a href="${ctx}/admin/article/list"><i class="fa fa-book"></i> 所有文章</a>
                    </li>
                    <li class="${param.pageMenu=='article_new'?'active':''}">
                        <a href="${ctx}/admin/article/new"><i class="fa fa-pencil-square-o"></i>写文章</a>
                    </li>
                    <li class="${param.pageMenu=='article_category'?'active':''}">
                        <a href="${ctx}/admin/article/category"><i class="fa fa-language"></i>分类目录</a>
                    </li>
                    <li class="${param.pageMenu=='article_tags'?'active':''}"><a href="${ctx}/admin/article/tags"><i class="fa fa-tags"></i> 标签</a>
                    </li>
                </ul>
            </li>
            <!-- 页面 -->
            <li class="treeview ${param.pageCatalog=='page'?'active':''}">
                <a href="#">
                    <i class="fa fa-sticky-note-o"></i><span>页面</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.pageMenu=='page_list'?'active':''}"><a href="${ctx}/admin/page/list"><i class="fa fa-clone"></i>所有页面</a></li>
                    <li class="${param.pageMenu=='page_new'?'active':''}"><a href="${ctx}/admin/page/new"><i class="fa fa-file-text-o"></i>新建页面</a>
                    </li>
                </ul>
            </li>
            <!-- 多媒体 -->
            <li class="treeview ${param.pageCatalog=='media'?'active':''}">
                <a href="#">
                    <i class="fa fa-picture-o"></i><span>多媒体</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.pageMenu=='media_list'?'active':''}"><a href="${ctx}/admin/media/list"><i class="fa fa-suitcase"></i>媒体库</a>
                    </li>
                    <li class="${param.pageMenu=='media_upload'?'active':''}"><a href="${ctx}/admin/media/upload"><i class="fa fa-upload"></i>添加</a>
                    </li>
                </ul>
            </li>
            <!-- 链接 -->
            <li class="${param.pageCatalog=='link'?'active':''}">
                <a href="${ctx}/admin/link">
                    <i class="fa fa-link"></i><span>链接</span>
                </a>
            </li>
            <!-- 评论 -->
            <li class="${param.pageCatalog=='comment'?'active':''}">
                <a href="${ctx}/admin/comment">
                    <i class="fa fa-comments"></i><span>评论</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                    <span class="pull-right-container">
		                <small class="label pull-right bg-blue">3</small>
	               </span>
                </a>
            </li>
            <!-- 用户 -->
            <li class="treeview ${param.pageCatalog=='user'?'active':''}">
                <a href="#">
                    <i class="fa fa-user"></i><span>用户</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.pageMenu=='user_list'?'active':''}"><a href="${pageContext.request.contextPath}/admin/user/list"><i class="fa fa-users"></i>所有用户</a></li>
                    <li class="${param.pageMenu=='user_add'?'active':''}"><a href="${ctx}/admin/user/editUserPage"><i class="fa fa-user-plus"></i>添加用户</a></li>
                    <li class="${param.pageMenu=='user_info'?'active':''}"><a href="${ctx}/admin/user/editMyselfPage"><i class="fa fa-black-tie"></i>我的资料</a>
                    </li>
                </ul>
            </li>
            <!-- 工具 -->
            <li class="treeview ${param.pageCatalog=='tools'?'active':''}">
                <a href="#">
                    <i class="fa fa-wrench"></i><span>工具</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.pageMenu=='tools_import'?'active':''}"><a href="${ctx}/admin/tools/import"><i class="fa fa-level-down"></i>数据导入</a>
                    </li>
                    <li class="${param.pageMenu=='tools_export'?'active':''}"><a href="${ctx}/admin/tools/export"><i
                            class="fa fa-level-up"></i>数据导出</a>
                    </li>
                </ul>
            </li>
            <!-- 设置 -->
            <li class="treeview ${param.pageCatalog=='setup'?'active':''}">
                <a href="#">
                    <i class="fa fa-cogs"></i><span>设置</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.pageMenu=='setup_normal'?'active':''}"><a href="${ctx}/admin/setup/normal"><i class="fa fa-cog"></i>常规</a></li>
                    <li class="${param.pageMenu=='setup_writing'?'active':''}"><a href="${ctx}/admin/setup/writing"><i class="fa fa-pencil"></i>撰写</a>
                    </li>
                    <li class="${param.pageMenu=='setup_reading'?'active':''}"><a href="${ctx}/admin/setup/reading"><i class="fa fa-leaf"></i>阅读</a>
                    </li>
                    <li class="${param.pageMenu=='setup_discussion'?'active':''}"><a href="${ctx}/admin/setup/discussion"><i
                            class="fa fa-commenting-o"></i>评论</a>
                    </li>
                    <li class="${param.pageMenu=='setup_media'?'active':''}"><a href="${ctx}/admin/setup/media"><i class="fa fa-folder-o"></i>多媒体</a>
                    </li>
                </ul>
            </li>
        </ul>
    </section>
</aside>