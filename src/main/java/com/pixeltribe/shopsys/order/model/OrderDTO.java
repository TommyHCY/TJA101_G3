package com.pixeltribe.shopsys.order.model;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pixeltribe.shopsys.orderItem.model.OrderItemDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private Integer orderNo;
	private Integer memNo;
	private Integer couponWalletNo;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Taipei")
	private Instant orderDatetime;
	
	private Integer proNo;
	private String proName;
	
	private String orderStatus;
	private Integer orderTotal;
	private Integer discountAmount;    // OrderService計算後設定
	private List<OrderItemDTO> orderItems;
	
	// 聯絡E-mail (
	private String contactEmail;
	
	// 計算實際付款的金額  (在OrderService設定discountAmount)
	public Integer getActualPayment() {
		if (discountAmount != null && discountAmount > 0) {
			return orderTotal - discountAmount;
		}
		return orderTotal;
	}
	
	
	
	
	
	// 檢查是否使用優惠券票夾
	public boolean hasUsedCouponWallet() {
		return couponWalletNo != null;
	}
	
	// 獲取訂單狀態資訊 (有中文的呈現跟數字百分比)
	public OrderStatusInfo getOrderStatusInfo() {
		switch (orderStatus) {
			case "PENDING":
				return new OrderStatusInfo("等待付款", 20);
			case "PAYING":              
	            return new OrderStatusInfo("付款處理中", 40);
			case "PROCESSING":
				return new OrderStatusInfo("處理中", 60);
			case "SHIPPED":
				return new OrderStatusInfo("已出貨", 80);
			case "COMPLETED":
				return new OrderStatusInfo("已完成", 100);
			case "FAILED":
				return new OrderStatusInfo("處理失敗", 0);
			case "CANCELLED":
				return new OrderStatusInfo("已取消", 0);
			default:
				return new OrderStatusInfo(orderStatus, 0);
			}
	}
	
	
	// ========= 內部類別 訂單狀態資訊 ========= //
	public static class OrderStatusInfo {
		private final String displayName;
		private final int progressPercentage; 
		
		public OrderStatusInfo(String displayName, int progressPercentage) {
            this.displayName = displayName;
            this.progressPercentage = progressPercentage;
        }
		
		public String getDisplayName() {
            return displayName;
        }
        
        public int getProgressPercentage() {
            return progressPercentage;
        }
        
        // 獲取進度條顏色（可用於前台樣式）
        public String getProgressColor() {
            if (progressPercentage == 100) {
                return "success";   // 綠色 - 已完成
            } else if (progressPercentage == 0) {
                return "danger";    // 紅色 - 失敗/取消
            } else if (progressPercentage >= 80) {
                return "warning";   // 橙色 - 處理中/已出貨
            } else if (progressPercentage >= 40) {  // 新增這個範圍給 PAYING
                return "primary";   // 藍色 - 付款處理中
            } else {
                return "info";      // 淺藍色 - 等待付款
            }
        }
        
        // 檢查是否已完成
        public boolean isCompleted() {
            return progressPercentage == 100;
        }
        
	}
	
	
	// ========= 訂單狀態的檢查方法 ========= //
	
	// ***** 檢查是否正在付款 ***** //
	public boolean isPaying() {
		return "PAYING".equals(orderStatus);
	}
	
	// ***** 檢查是否可以取消 (付款終究不能取消了! 只有等待付款時可以取消) ***** //
	public boolean canBeCancelled() {
		return "PENDING".equals(orderStatus) || 
		       "PAYING".equals(orderStatus) || 
		       "PROCESSING".equals(orderStatus);
		    // 注意：SHIPPED 和 COMPLETED 不能取消
		}
	
	// ***** 檢查是否可以重新付款 (避免用戶有信用卡結帳問題、網路問題、操作問題等狀況發生)***** //
	public boolean canRetryPayment() {
		return "FAILED".equals(orderStatus) || "PENDING".equals(orderStatus);
	}
	
	// ***** 檢查訂單是否處於最終狀態 ***** //
	public boolean isFinalStatus() {
		return "COMPLETED".equals(orderStatus) || 
		       "CANCELLED".equals(orderStatus);
	}
	
	
	
	// 加入getProductName方法(Email)
		public String getProductName() {
		    // 第一優先：使用 proName
		    if (proName != null && !proName.trim().isEmpty()) {
		        return proName;
		    }
		    
		    // 第二優先：如果有產品ID，使用產品ID
		    if (proNo != null) {
		        return "產品 #" + proNo;
		    }
		    
		    // 最後兜底：返回預設值
		    return "未知產品";
		}
		
		// 檢查產品名稱是否有效(Email)
		public boolean hasValidProductName() {
		    return proName != null && !proName.trim().isEmpty();
		}
		
		// 獲取顯示用的產品名稱（包含ID）
		public String getDisplayProductName() {
		    if (hasValidProductName()) {
		        if (proNo != null) {
		            return proName + " (#" + proNo + ")";
		        } else {
		            return proName;
		        }
		    } else {
		        return getProductName(); // 使用安全的後備方案
		    }
		}
	
}