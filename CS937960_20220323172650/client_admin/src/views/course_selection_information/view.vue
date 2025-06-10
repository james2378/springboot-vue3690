<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','course_number') || $check_field('add','course_number') || $check_field('set','course_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="课程编号" prop="course_number">
					<el-input id="course_number" v-model="form['course_number']" placeholder="请输入课程编号"
							  v-if="user_group === '管理员' || (form['course_selection_information_id'] && $check_field('set','course_number')) || (!form['course_selection_information_id'] && $check_field('add','course_number'))" :disabled="disabledObj['course_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','course_number')">{{form['course_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','course_name') || $check_field('add','course_name') || $check_field('set','course_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="课程名称" prop="course_name">
					<el-input id="course_name" v-model="form['course_name']" placeholder="请输入课程名称"
							  v-if="user_group === '管理员' || (form['course_selection_information_id'] && $check_field('set','course_name')) || (!form['course_selection_information_id'] && $check_field('add','course_name'))" :disabled="disabledObj['course_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','course_name')">{{form['course_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_information') || $check_field('add','student_information') || $check_field('set','student_information')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学生信息" prop="student_information">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_student_information(form['student_information']) }}
							<!--<el-input id="business_name" v-model="form['student_information']" placeholder="请输入学生信息"-->
							<!--v-if="user_group === '管理员' || (form['course_selection_information_id'] && $check_field('set','student_information')) || (!form['course_selection_information_id'] && $check_field('add','student_information'))" :disabled="disabledObj['student_information_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','student_information')">{{form['student_information']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['course_selection_information_id'] && $check_field('set','student_information')) || (!form['course_selection_information_id'] && $check_field('add','student_information'))" id="student_information" v-model="form['student_information']" :disabled="disabledObj['student_information_isDisabled']">
								<el-option v-for="o in list_user_student_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','student_information')" id="student_information" v-model="form['student_information']" :disabled="true">
								<el-option v-for="o in list_user_student_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="student_information" v-model="form['student_information']" :disabled="disabledObj['student_information_isDisabled']">
							<el-option v-for="o in list_user_student_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['course_selection_information_id'] && $check_field('set','full_name')) || (!form['course_selection_information_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_number') || $check_field('add','student_number') || $check_field('set','student_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学号" prop="student_number">
					<el-input id="student_number" v-model="form['student_number']" placeholder="请输入学号"
							  v-if="user_group === '管理员' || (form['course_selection_information_id'] && $check_field('set','student_number')) || (!form['course_selection_information_id'] && $check_field('add','student_number'))" :disabled="disabledObj['student_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_number')">{{form['student_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','elective_semester') || $check_field('add','elective_semester') || $check_field('set','elective_semester')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="选课学期" prop="elective_semester">
					<el-input id="elective_semester" v-model="form['elective_semester']" placeholder="请输入选课学期"
							  v-if="user_group === '管理员' || (form['course_selection_information_id'] && $check_field('set','elective_semester')) || (!form['course_selection_information_id'] && $check_field('add','elective_semester'))" :disabled="disabledObj['elective_semester_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','elective_semester')">{{form['elective_semester']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','notes_on_course_selection') || $check_field('add','notes_on_course_selection') || $check_field('set','notes_on_course_selection')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="选课备注" prop="notes_on_course_selection">
					<el-input type="textarea" id="notes_on_course_selection" v-model="form['notes_on_course_selection']" placeholder="请输入选课备注"
						v-if="user_group === '管理员' || (form['course_selection_information_id'] && $check_field('set','notes_on_course_selection')) || (!form['course_selection_information_id'] && $check_field('add','notes_on_course_selection'))" :disabled="disabledObj['notes_on_course_selection_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','notes_on_course_selection')">{{form['notes_on_course_selection']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "course_selection_information_id",
				url_add: "~/api/course_selection_information/add?",
				url_set: "~/api/course_selection_information/set?",
				url_get_obj: "~/api/course_selection_information/get_obj?",
				url_upload: "~/api/course_selection_information/upload?",

				query: {
					"course_selection_information_id": 0,
				},

				form: {
					"course_number":'', // 课程编号
					"course_name":'', // 课程名称
					"student_information": 0, // 学生信息
					"full_name":'', // 姓名
					"student_number":'', // 学号
					"elective_semester":'', // 选课学期
					"notes_on_course_selection":'', // 选课备注
					"course_selection_information_id": 0, // ID

				},
				disabledObj:{
					"course_number_isDisabled": false,
					"course_name_isDisabled": false,
					"student_information_isDisabled": false,
					"full_name_isDisabled": false,
					"student_number_isDisabled": false,
					"elective_semester_isDisabled": false,
					"notes_on_course_selection_isDisabled": false,
				},
				// 用户列表
				list_user_student_information: [],
				// 用户组
				group_user_student_information: "",

			}
		},
		methods: {
			/**
			 * 获取学生用户用户列表
			 */
			async get_list_user_student_information() {
                // if(this.user_group !== "管理员" && this.form["student_information"] === 0) {
                //     this.form["student_information"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生用户");
                if(json.result && json.result.list){
                    this.list_user_student_information = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取学生用户用户组
			 */
			async get_group_user_student_information() {
				this.form["student_information"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=学生用户");
				if(json.result && json.result.obj){
					this.group_user_student_information = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_student_information(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_student_information.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="student_information") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_student_information(id){
				var obj = this.list_user_student_information.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "学生信息":
							if(param["student_information"] > 0){
								param["student_information"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/course_selection_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/course_selection_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/course_selection_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/course_selection_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/course_selection_information/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_student_information();
			this.get_group_user_student_information();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
