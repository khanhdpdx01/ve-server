/**
 *    SPDX-License-Identifier: Apache-2.0
 */

import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import clientJson from '../../../package.json';
import FabricVersion from '../../FabricVersion';

const styles = theme => {
	const { type } = theme.palette;
	const dark = type === 'dark';
	return {
		root: {
			margin: '2%'
		},
		footer: {
			textAlign: 'center',
			marginTop: '30px',
			marginBottom: '20px'
		}
	};
};

const FooterView = ({ classes }) => (
	<div className={classes.root}>
		{/* <div>
			<div className={classes.footer}>
				{'Hyperledger Explorer Client Version: '}
				{clientJson.version}
				&emsp;
				{'Fabric Compatibility: '} {FabricVersion.map(v => v)}
			</div>
		</div> */}
		<div>
			<div className={classes.footer}>
				{
					'Cơ quan chủ quản: Tổng Công ty Giải pháp Doanh nghiệp Viettel – Chi nhánh Tập đoàn Công nghiệp – Viễn thông Quân đội'
				}
				<br></br>
				{
					'Địa chỉ: Số 1 đường Trần Hữu Dực, Phường Mỹ Đình 2, Quận Nam Từ Liêm, Hà Nội, Việt Nam'
				}
			</div>
		</div>
	</div>
);

export default withStyles(styles)(FooterView);
