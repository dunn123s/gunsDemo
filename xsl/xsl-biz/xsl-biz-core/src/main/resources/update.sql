ALTER TABLE `xsldb`.`t_jackpot_proposal`
ADD COLUMN `apply_time` datetime NULL AFTER `issue_remark`;


update t_jackpot_proposal set apply_time = created_time;
