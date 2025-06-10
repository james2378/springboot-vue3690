<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','title_name') || $check_field('add','title_name') || $check_field('set','title_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="标题名称" prop="title_name">
					<el-input id="title_name" v-model="form['title_name']" placeholder="请输入标题名称"
							  v-if="user_group === '管理员' || (form['announcement_notice_id'] && $check_field('set','title_name')) || (!form['announcement_notice_id'] && $check_field('add','title_name'))" :disabled="disabledObj['title_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','title_name')">{{form['title_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','publisher') || $check_field('add','publisher') || $check_field('set','publisher')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="发布人" prop="publisher">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_publisher(form['publisher']) }}
							<!--<el-input id="business_name" v-model="form['publisher']" placeholder="请输入发布人"-->
							<!--v-if="user_group === '管理员' || (form['announcement_notice_id'] && $check_field('set','publisher')) || (!form['announcement_notice_id'] && $check_field('add','publisher'))" :disabled="disabledObj['publisher_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','publisher')">{{form['publisher']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['announcement_notice_id'] && $check_field('set','publisher')) || (!form['announcement_notice_id'] && $check_field('add','publisher'))" id="publisher" v-model="form['publisher']" :disabled="disabledObj['publisher_isDisabled']">
								<el-option v-for="o in list_user_publisher" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','publisher')" id="publisher" v-model="form['publisher']" :disabled="true">
								<el-option v-for="o in list_user_publisher" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="publisher" v-model="form['publisher']" :disabled="disabledObj['publisher_isDisabled']">
							<el-option v-for="o in list_user_publisher" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','announcement_content') || $check_field('add','announcement_content') || $check_field('set','announcement_content')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="公告内容" prop="announcement_content">
					<el-input type="textarea" id="announcement_content" v-model="form['announcement_content']" placeholder="请输入公告内容"
						v-if="user_group === '管理员' || (form['announcement_notice_id'] && $check_field('set','announcement_content')) || (!form['announcement_notice_id'] && $check_field('add','announcement_content'))" :disabled="disabledObj['announcement_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','announcement_content')">{{form['announcement_content']}}</div>
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
				field: "announcement_notice_id",
				url_add: "~/api/announcement_notice/add?",
				url_set: "~/api/announcement_notice/set?",
				url_get_obj: "~/api/announcement_notice/get_obj?",
				url_upload: "~/api/announcement_notice/upload?",

				query: {
					"announcement_notice_id": 0,
				},

				form: {
					"title_name":'', // 标题名称
					"publisher": 0, // 发布人
					"announcement_content":'', // 公告内容
					"announcement_notice_id": 0, // ID

				},
				disabledObj:{
					"title_name_isDisabled": false,
					"publisher_isDisabled": false,
					"announcement_content_isDisabled": false,
				},
				// 用户列表
				list_user_publisher: [],
				// 用户组
				group_user_publisher: "",

			}
		},
		methods: {
			/**
			 * 获取教师用户用户列表
			 */
			async get_list_user_publisher() {
                // if(this.user_group !== "管理员" && this.form["publisher"] === 0) {
                //     this.form["publisher"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=教师用户");
                if(json.result && json.result.list){
                    this.list_user_publisher = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取教师用户用户组
			 */
			async get_group_user_publisher() {
				this.form["publisher"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=教师用户");
				if(json.result && json.result.obj){
					this.group_user_publisher = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_publisher(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_publisher.source_table+"/get_obj?"
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
									if (arr[i]!=="publisher") {
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
			get_user_publisher(id){
				var obj = this.list_user_publisher.getObj({"user_id":id});
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
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "发布人":
							if(param["publisher"] > 0){
								param["publisher"] = this.user.user_id;
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
					bl = this.$check_action('/announcement_notice/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/announcement_notice/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/announcement_notice/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/announcement_notice/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/announcement_notice/view','get');
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
			this.get_list_user_publisher();
			this.get_group_user_publisher();
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
