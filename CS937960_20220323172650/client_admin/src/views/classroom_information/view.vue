<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','classroom_name') || $check_field('add','classroom_name') || $check_field('set','classroom_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="教室名称" prop="classroom_name">
					<el-input id="classroom_name" v-model="form['classroom_name']" placeholder="请输入教室名称"
							  v-if="user_group === '管理员' || (form['classroom_information_id'] && $check_field('set','classroom_name')) || (!form['classroom_information_id'] && $check_field('add','classroom_name'))" :disabled="disabledObj['classroom_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','classroom_name')">{{form['classroom_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','classroom_pictures') || $check_field('add','classroom_pictures') || $check_field('set','classroom_pictures')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="教室图片" prop="classroom_pictures">
					<el-upload :disabled="disabledObj['classroom_pictures_isDisabled']" id="classroom_pictures" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_classroom_pictures"
						:show-file-list="false" v-if="user_group === '管理员' || (form['classroom_information_id'] && $check_field('set','classroom_pictures')) || (!form['classroom_information_id'] && $check_field('add','classroom_pictures'))">
						<img v-if="form['classroom_pictures']" :src="$fullUrl(form['classroom_pictures'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','classroom_pictures')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['classroom_pictures'])" :preview-src-list="[$fullUrl(form['classroom_pictures'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','classroom_leader') || $check_field('add','classroom_leader') || $check_field('set','classroom_leader')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="教室负责人" prop="classroom_leader">
						<el-select v-if="user_group === '管理员' || (form['classroom_information_id'] && $check_field('set','classroom_leader')) || (!form['classroom_information_id'] && $check_field('add','classroom_leader'))" id="classroom_leader" v-model="form['classroom_leader']" :disabled="disabledObj['classroom_leader_isDisabled']">
							<el-option v-for="o in list_user_classroom_leader" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','classroom_leader')" id="classroom_leader" v-model="form['classroom_leader']" :disabled="true">
							<el-option v-for="o in list_user_classroom_leader" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','capacity') || $check_field('add','capacity') || $check_field('set','capacity')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="可容纳人数" prop="capacity">
					<el-input-number id="capacity" v-model.number="form['capacity']"
						v-if="user_group === '管理员' || (form['classroom_information_id'] && $check_field('set','capacity')) || (!form['classroom_information_id'] && $check_field('add','capacity'))"></el-input-number>
					<div v-else-if="$check_field('get','capacity')">{{form['capacity']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','equipment_information') || $check_field('add','equipment_information') || $check_field('set','equipment_information')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="设备信息" prop="equipment_information">
					<el-input type="textarea" id="equipment_information" v-model="form['equipment_information']" placeholder="请输入设备信息"
						v-if="user_group === '管理员' || (form['classroom_information_id'] && $check_field('set','equipment_information')) || (!form['classroom_information_id'] && $check_field('add','equipment_information'))" :disabled="disabledObj['equipment_information_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','equipment_information')">{{form['equipment_information']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','classroom_location') || $check_field('add','classroom_location') || $check_field('set','classroom_location')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="教室位置" prop="classroom_location">
					<el-input type="textarea" id="classroom_location" v-model="form['classroom_location']" placeholder="请输入教室位置"
						v-if="user_group === '管理员' || (form['classroom_information_id'] && $check_field('set','classroom_location')) || (!form['classroom_information_id'] && $check_field('add','classroom_location'))" :disabled="disabledObj['classroom_location_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','classroom_location')">{{form['classroom_location']}}</div>
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
				field: "classroom_information_id",
				url_add: "~/api/classroom_information/add?",
				url_set: "~/api/classroom_information/set?",
				url_get_obj: "~/api/classroom_information/get_obj?",
				url_upload: "~/api/classroom_information/upload?",

				query: {
					"classroom_information_id": 0,
				},

				form: {
					"classroom_name":'', // 教室名称
					"classroom_pictures":'', // 教室图片
					"classroom_leader": 0, // 教室负责人
					"capacity":0, // 可容纳人数
					"equipment_information":'', // 设备信息
					"classroom_location":'', // 教室位置
					"classroom_information_id": 0, // ID

				},
				disabledObj:{
					"classroom_name_isDisabled": false,
					"classroom_pictures_isDisabled": false,
					"classroom_leader_isDisabled": false,
					"equipment_information_isDisabled": false,
					"classroom_location_isDisabled": false,
				},
				// 用户列表
				list_user_classroom_leader: [],

			}
		},
		methods: {
			/**
			 * 上传教室图片
			 * @param {Object} param图片参数
			 */
			upload_classroom_pictures(param){
				this.uploadFile(param.file, "classroom_pictures");
			},
			/**
			 * 获取教师用户用户列表
			 */
			async get_list_user_classroom_leader() {
                // if(this.user_group !== "管理员" && this.form["classroom_leader"] === 0) {
                //     this.form["classroom_leader"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=教师用户");
                if(json.result && json.result.list){
                    this.list_user_classroom_leader = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_classroom_leader(id){
				var obj = this.list_user_classroom_leader.getObj({"user_id":id});
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
					bl = this.$check_action('/classroom_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/classroom_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/classroom_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/classroom_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/classroom_information/view','get');
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
			this.get_list_user_classroom_leader();
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
