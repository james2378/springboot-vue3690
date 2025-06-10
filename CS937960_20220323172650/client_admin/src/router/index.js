import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index,
		meta: {
			index: 0,
			title: '首页'
		}
	},

	// 登录
	{
		path: '/login',
		name: 'login',
		component: login,
		meta: {
			index: 0,
			title: '登录'
		}
	},

	// 忘记密码
	{
		path: '/forgot',
		name: "forgot",
		component: forgot,
		meta: {
			index: 0,
			title: '忘记密码'
		}
	},

	// 修改密码
	{
		path: '/user/password',
		name: "password",
		component: () => import("../views/user/password.vue"),
		meta: {
			index: 0,
			title: '修改密码'
		}
	},

	// 视频播放页
	{
		path: "/media/video",
		name: "video",
		component: () => import('../views/media/video.vue'),
		meta: {
			index: 0,
			title: "视频"
		}
	},

	// 音频播放页
	{
		path: "/media/audio",
		name: "audio",
		component: () => import('../views/media/audio.vue'),
		meta: {
			index: 0,
			title: "音频"
		}
	},
	// 学生用户路由
	{
		path: '/student_user/table',
		name: 'student_user_table',
		component: () => import('../views/student_user/table.vue')
	},
	{
		path: '/student_user/view',
		name: 'student_user_view',
		component: () => import('../views/student_user/view.vue')
	},
	// 教师用户路由
	{
		path: '/teacher_user/table',
		name: 'teacher_user_table',
		component: () => import('../views/teacher_user/table.vue')
	},
	{
		path: '/teacher_user/view',
		name: 'teacher_user_view',
		component: () => import('../views/teacher_user/view.vue')
	},
	// 公告通知路由
	{
		path: '/announcement_notice/table',
		name: 'announcement_notice_table',
		component: () => import('../views/announcement_notice/table.vue')
	},
	{
		path: '/announcement_notice/view',
		name: 'announcement_notice_view',
		component: () => import('../views/announcement_notice/view.vue')
	},
	// 选课信息路由
	{
		path: '/course_selection_information/table',
		name: 'course_selection_information_table',
		component: () => import('../views/course_selection_information/table.vue')
	},
	{
		path: '/course_selection_information/view',
		name: 'course_selection_information_view',
		component: () => import('../views/course_selection_information/view.vue')
	},
	// 课程信息路由
	{
		path: '/course_information/table',
		name: 'course_information_table',
		component: () => import('../views/course_information/table.vue')
	},
	{
		path: '/course_information/view',
		name: 'course_information_view',
		component: () => import('../views/course_information/view.vue')
	},
	// 成绩信息路由
	{
		path: '/achievement_information/table',
		name: 'achievement_information_table',
		component: () => import('../views/achievement_information/table.vue')
	},
	{
		path: '/achievement_information/view',
		name: 'achievement_information_view',
		component: () => import('../views/achievement_information/view.vue')
	},
	// 学院信息路由
	{
		path: '/college_information/table',
		name: 'college_information_table',
		component: () => import('../views/college_information/table.vue')
	},
	{
		path: '/college_information/view',
		name: 'college_information_view',
		component: () => import('../views/college_information/view.vue')
	},
	// 教室信息路由
	{
		path: '/classroom_information/table',
		name: 'classroom_information_table',
		component: () => import('../views/classroom_information/table.vue')
	},
	{
		path: '/classroom_information/view',
		name: 'classroom_information_view',
		component: () => import('../views/classroom_information/view.vue')
	},

	// 用户路由
	{
		path: '/user/table',
		name: 'user_table',
		component: () => import('../views/user/table.vue'),
		meta: {
			index: 0,
			title: '用户列表'
		}
	},
	{
		path: '/user/view',
		name: 'user_view',
		component: () => import('../views/user/view.vue'),
		meta: {
			index: 0,
			title: '用户详情'
		}
	},
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue'),
		meta: {
			index: 0,
			title: '个人信息'
		}
	},
	// 用户组路由
	{
		path: '/user_group/table',
		name: 'user_group_table',
		component: () => import('../views/user_group/table.vue'),
		meta: {
			index: 0,
			title: '用户组列表'
		}
	},
	{
		path: '/user_group/view',
		name: 'user_group_view',
		component: () => import('../views/user_group/view.vue'),
		meta: {
			index: 0,
			title: '用户组详情'
		}
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {
	let token = to.query.token;
	if (token){
		$.db.set("token",token,120);
	}
	next();
})

router.afterEach((to, from, next) => {
	let title = "学生选课系统-admin";
	document.title = title;
})

export default router
