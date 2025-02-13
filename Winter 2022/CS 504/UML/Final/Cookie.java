public abstract class Cookie {
	double base;
	double content;
	double shape;
	double cookieTotal;

	Cookie(double cookieTotal) {
		this.cookieTotal = cookieTotal;
	}

	double getBase() {
		return base;
	}

	double getContent() {
		return content;
	}

	double getShape() {
		return shape;
	}

	abstract double calculateBaseValue();

	abstract double calculateContentValue();

	abstract double calculateShapeValue();

	double calculateTotal() {
		return (calculateBaseValue()) + (calculateContentValue()) + calculateShapeValue();
	}

	public static abstract class Builder {
		Cookie cookie;

		public Builder base(Double base) {
			cookie.base = base;
			return this;
		}

		public Builder content(Double content) {
			cookie.content = content;
			return this;
		}

		public Builder shape(Double shape) {
			cookie.shape = shape;
			return this;
		}

		private void validate(Cookie cookie) {
			String cookieBase = "";
			String cookieContent = "";
			String cookieShape = "";
			String cookieTotal = "";
			if (cookie.base <= 0) {
				cookieBase = "base ";
			}
			if (cookie.content <= 0) {
				cookieContent = "content ";
			}
			if (cookie.shape <= 0) {
				cookieShape = "shape ";
			}
			if (cookie.cookieTotal <= 0) {
				cookieTotal = "total ";
			}
			if (cookie.base <= 0 || cookie.content <= 0 || cookie.shape <= 0 || cookie.cookieTotal <= 0) {
				throw new IllegalStateException(cookieContent + cookieBase + cookieShape + cookieTotal);
			}

		}

		public Cookie build() {
			validate(cookie);
			return cookie;
		}

	}
}
